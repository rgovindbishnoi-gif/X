package com.example.data.ai

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val sender: MessageSender,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isSafetyWarning: Boolean = false
)

enum class MessageSender {
    USER,
    ASSISTANT
}

object BikeAssistantEngine {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private const val SYSTEM_INSTRUCTION = """
You are Bike Assistant, an expert motorcycle technician, safety advisor, and mechanic.
Guidelines:
1. Always prioritize rider safety. If an issue involves brakes, tires, steering, oil leaks, or frame cracks, strongly advise against riding.
2. Never pretend to diagnose a motorcycle with 100% certainty from text alone. Always offer the most probable causes and ask clarifying questions (e.g., when does it happen? hot or cold? RPM-dependent?).
3. Clearly distinguish general motorcycle principles from motorcycle-specific instructions; emphasize that riders must verify exact specs (fluids, torques, clearances) in their official manufacturer service manual.
4. Recommend a qualified professional motorcycle technician for dangerous, high-voltage, internal engine, or complex fuel/electrical problems.
5. Provide clear, step-by-step, actionable advice suitable for both beginners and experienced motorcyclists.
"""

    suspend fun askAssistant(prompt: String, bikeContext: String?): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }

        // Try Gemini REST API if key is available and non-placeholder
        if (apiKey.isNotBlank() && apiKey != "MY_GEMINI_API_KEY") {
            try {
                val fullContext = if (!bikeContext.isNullOrBlank()) {
                    "Current Motorcycle Context: $bikeContext\n\nUser Question: $prompt"
                } else {
                    prompt
                }

                val jsonPayload = JSONObject().apply {
                    val contentsArr = JSONArray().apply {
                        put(JSONObject().apply {
                            put("role", "user")
                            put("parts", JSONArray().apply {
                                put(JSONObject().put("text", fullContext))
                            })
                        })
                    }
                    put("contents", contentsArr)

                    val sysInstructionObj = JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().put("text", SYSTEM_INSTRUCTION))
                        })
                    }
                    put("systemInstruction", sysInstructionObj)
                }

                val body = jsonPayload.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
                    .post(body)
                    .build()

                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseStr = response.body?.string().orEmpty()
                    val responseJson = JSONObject(responseStr)
                    val candidate = responseJson.optJSONArray("candidates")?.optJSONObject(0)
                    val textPart = candidate?.optJSONObject("content")
                        ?.optJSONArray("parts")?.optJSONObject(0)?.optString("text")

                    if (!textPart.isNullOrBlank()) {
                        return@withContext textPart.trim()
                    }
                }
            } catch (e: Exception) {
                // Fallback to offline rule-based knowledge engine
            }
        }

        // Dedicated offline diagnostic motorcycle technician engine
        generateOfflineExpertResponse(prompt, bikeContext)
    }

    private fun generateOfflineExpertResponse(prompt: String, bikeContext: String?): String {
        val p = prompt.lowercase()
        val contextNote = if (!bikeContext.isNullOrBlank()) " (Noted for: $bikeContext)" else ""

        return when {
            p.contains("noise") || p.contains("sound") || p.contains("ticking") || p.contains("knocking") || p.contains("rattle") -> {
                """
🔧 **Bike Assistant Diagnostic: Engine & Mechanical Noise**$contextNote

When evaluating motorcycle noises, the location and timing provide crucial clues:
1. **High-Frequency Ticking from Cylinder Head:**
   - *Most common causes:* Normal fuel injector ticking, Cam Chain Tensioner (CCT) wear, or valve clearances that have loosened.
   - *Clarifying Question:* Does the ticking speed up smoothly with RPM? Does it stay when the engine reaches full operating temperature?
2. **Heavy Dull Knocking from Lower Engine:**
   - *Critical Alert:* A deep thud from the crankcase that gets louder under acceleration often indicates rod bearing wear.
   - ⚠️ **Safety Rule:** If you hear heavy metal knocking, turn the engine OFF immediately. Continuing to ride risks complete engine seizure and rear-wheel lockup.
3. **Slapping / Rattling on Deceleration:**
   - Usually a slack drive chain hitting the swingarm buffer guide. Check chain slack (typically 25–35 mm).

*General Guidance:* Always check your engine oil level on level ground first. For internal engine sounds, consult a certified motorcycle technician for a stethoscope inspection.
                """.trimIndent()
            }

            p.contains("warning light") || p.contains("mil") || p.contains("fi light") || p.contains("check engine") -> {
                """
⚠️ **Bike Assistant: Warning Lights & Indicators**$contextNote

Modern motorcycle dashboards communicate critical safety and system status:
1. **Red Oil Pressure Light (Oil Can Symbol):**
   - 🛑 **STOP IMMEDIATELY.** This indicates a critical loss of oil pressure, NOT just low oil. Riding even 1 minute with zero oil pressure will destroy engine bearings.
2. **Yellow/Amber FI or Engine Light:**
   - Indicates an electronic sensor fault (e.g., O2 sensor, throttle position sensor, side-stand sensor) recorded by the ECU.
   - If the bike runs smoothly and engine temperature is normal, it is generally safe to ride home or to a workshop, but schedule an OBD code scan.
3. **ABS Warning Light:**
   - Stays on during startup until you roll forward above ~5–10 km/h. If it remains illuminated while riding, your standard brakes work, but anti-lock skid protection is inactive.
4. **Coolant High Temperature Light (Red Thermometer):**
   - Pull over safely into shade and turn off the engine. Check the cooling fan and overflow tank. Never open the radiator cap while hot!

*Consult your motorcycle's owner's manual to verify model-specific flash codes.*
                """.trimIndent()
            }

            p.contains("long ride") || p.contains("trip") || p.contains("touring") || p.contains("travel") -> {
                """
🗺️ **Bike Assistant: Long-Ride Pre-Departure Briefing**$contextNote

Before setting off on a multi-day or long-distance ride, complete this systematic safety check:
1. **Tyres (Highest Priority):**
   - Check cold pressure with an accurate gauge. Adjust +2 PSI if carrying heavy luggage or a passenger.
   - Inspect tread depth: remember tyre wear accelerates on hot highway runs!
2. **Vital Fluids:**
   - Engine oil between MIN and MAX marks.
   - Coolant level in expansion reservoir.
   - Brake fluid reservoirs (light amber, not dark brown).
3. **Drive Chain:**
   - Clean, lubricate, and set slack. Pack a travel-sized chain lube can for daily re-application (every 500–800 km or after rain).
4. **Controls & Cables:**
   - Lubricate clutch and throttle cables; verify 2–3 mm lever free-play.
5. **Emergency Kit to Carry:**
   - Tubeless tire puncture plug kit + CO2 inflator canisters.
   - Compact multi-tool (hex keys, spanners, pliers).
   - High-visibility safety vest and compact first-aid kit.
   - Documents (registration, insurance, ID) in a waterproof pouch.

*Ride rested and take breaks every 1.5 to 2 hours to avoid cognitive fatigue!*
                """.trimIndent()
            }

            p.contains("clutch") -> {
                """
⚙️ **Bike Assistant: Understanding the Motorcycle Clutch**$contextNote

The clutch is your connection between the engine crankshaft and the transmission gearbox:
1. **How It Works:**
   - Most motorcycles use a **multi-plate wet clutch** bathed in the same engine oil that lubricates your motor.
   - Alternating friction plates (lined with composite material) and steel drive plates are pressed together by heavy clutch springs.
2. **Lever Operation:**
   - Pulling the clutch lever pushes the pressure plate away, allowing plates to slip freely so the bike can idle at a stop without stalling.
   - Releasing the lever clamps the plates together, transmitting 100% of engine power to the rear wheel.
3. **Slipper / Assist Clutches:**
   - Modern bikes use an assist & slipper mechanism. On aggressive downshifts, internal angled ramps slip the plates slightly to prevent dangerous rear-wheel hop or skid from back-torque.
4. **Maintenance Tips:**
   - Maintain 2–3 mm of free play at the clutch lever perch. Zero free play causes premature clutch slipping and burnt friction plates.
   - Always use motorcycle-specific JASO MA / MA2 certified oil (never automotive car oil with friction modifiers).
                """.trimIndent()
            }

            p.contains("overheat") || p.contains("temperature") || p.contains("coolant") -> {
                """
🌡️ **Bike Assistant: Motorcycle Overheating Causes & Actions**$contextNote

Motorcycles run hotter than cars due to compact engine packaging and high power-to-weight ratios:
1. **Immediate Safety Steps:**
   - Pull over safely and shut off the engine. Let the bike cool down for 30–45 minutes.
   - ⚠️ **NEVER open the radiator cap while the engine is hot.** Pressurized boiling steam can cause severe burns.
2. **Common Causes:**
   - **Low Coolant Level:** Check for hose leaks, cracked radiator tank, or weeping water pump seal.
   - **Radiator Fan Inoperative:** Check the radiator fan fuse and temperature sensor wire.
   - **Clogged Radiator Fins:** Dirt, mud, or insects blocking airflow. Clean gently with water spray (no high-pressure jet).
   - **Stuck Thermostat:** If closed, coolant cannot circulate into the radiator.
3. **Air-Cooled Motorcycles:**
   - Rely strictly on forward airflow. Extended idling in bumper-to-bumper city traffic causes severe heat soak. Pull over and let it cool if engine starts pinging or sluggishly responding.

*If coolant loss or white exhaust smoke occurs, consult a workshop to test for a blown cylinder head gasket.*
                """.trimIndent()
            }

            p.contains("chain") -> {
                """
⛓️ **Bike Assistant: Motorcycle Drive Chain Maintenance**$contextNote

Your drive chain transfers all horsepower directly to the road. Proper care doubles its service life:
1. **Service Frequency:**
   - Clean and lubricate every **500 to 800 km** (300–500 miles), and immediately after riding in heavy rain.
2. **Cleaning Procedure:**
   - Place bike on paddock stand with engine **OFF** and transmission in **NEUTRAL**.
   - ⚠️ **CRITICAL WARNING:** NEVER run the motorcycle in gear while cleaning the chain! Fingers can be instantly trapped and amputated between chain and rear sprocket.
   - Spray O-ring safe chain cleaner or deodorized kerosene. Scrub with a 3-sided grunge brush and wipe clean with shop rags.
3. **Lubrication:**
   - Apply lubricant to the **INNER** run of the chain (facing the sprockets) right after a ride when the chain is warm. Centrifugal force pushes the oil outward into the O-ring seals.
   - Let solvent evaporate for 20–30 minutes before riding to prevent fling onto your rear tyre and brake disc.
4. **Chain Slack:**
   - Measure vertical movement at midpoint between sprockets (standard is usually 25–35 mm). Both too tight (strains countershaft bearing) and too loose (risks jumping sprocket) are hazardous.
                """.trimIndent()
            }

            p.contains("service") || p.contains("servicing") || p.contains("maintenance") -> {
                """
📋 **Bike Assistant: Motorcycle Servicing Fundamentals**$contextNote

Motorcycles require disciplined maintenance because components operate at high RPMs in exposed outdoor environments:
1. **First Service (800 – 1,000 km):**
   - The most critical service. Flushes metallic break-in particles from engine and replaces the filter. Re-torques chassis fasteners.
2. **Regular Service Intervals (5,000 – 10,000 km or Annually):**
   - Fresh engine oil & filter (oil breaks down from time, combustion acids, and shear stresses).
   - Brake pad thickness check and brake fluid moisture testing.
   - Air filter inspection.
   - Battery voltage and charging system verification.
3. **High-Mileage Services (20,000 – 40,000 km):**
   - Valve clearance inspection and shim replacement.
   - Fork oil flush and seal renewal.
   - Steering head and wheel bearing inspection.
   - Spark plug replacement.

*Golden Rule:* Always follow the motorcycle manufacturer's official service schedule found in your owner's manual.
                """.trimIndent()
            }

            else -> {
                """
🏍️ **Bike Assistant at your service!**$contextNote

I am your motorcycle technical guide. To help pinpoint your question:
- Are you asking about mechanical troubleshooting, routine maintenance, riding safety, or motorcycle specifications?
- What symptoms are you observing (e.g. noises, vibration, fluid leaks, warning lights)?
- When does the issue occur (e.g. cold start, hard braking, high speed, turning)?

*Remember: Always verify manufacturer-specific specifications in your official owner's manual, and consult a qualified motorcycle technician for hazardous mechanical or braking repairs.*
                """.trimIndent()
            }
        }
    }
}
