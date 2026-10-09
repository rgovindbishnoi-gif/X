package com.example.data.repository

import com.example.data.model.*

object DemoDataProvider {

    val demoMotorcycles = listOf(
        MotorcycleSpec(
            id = "cb650r",
            brand = "Honda",
            model = "CB650R Neo Sports Café",
            modelYear = 2024,
            category = "Naked / Roadster",
            engineType = "Liquid-cooled 4-stroke 16-valve DOHC Inline-4",
            engineDisplacement = "649 cc",
            transmission = "6-speed manual with assist/slipper clutch",
            fuelSystem = "PGM-FI electronic fuel injection with 32mm throttle bores",
            coolingSystem = "Liquid-cooled with aluminium radiator and thermo-fan",
            frontBrake = "Dual 310mm floating discs with radial-mount 4-piston Nissin calipers, ABS",
            rearBrake = "Single 240mm disc with single-piston caliper, ABS",
            frontSuspension = "41mm Showa SFF-BP (Separate Function Big Piston) USD forks, 120mm travel",
            rearSuspension = "Monoshock damper with 10-stage preload adjustment, aluminium swingarm",
            tyreSizes = "Front: 120/70ZR17 (58W) | Rear: 180/55ZR17 (73W)",
            fuelCapacity = "15.4 Litres (incl. 3.0L reserve)",
            weight = "202.5 kg (kerb wet weight)",
            dimensions = "2,130 mm x 780 mm x 1,075 mm | Seat height: 810 mm | Wheelbase: 1,450 mm",
            oilCapacity = "2.9 Litres (dry), 2.3L (with filter change) - SAE 10W-30 / 10W-40 MA2",
            serviceIntervalKm = 6000,
            chainServiceIntervalKm = 800,
            isDemoData = true
        ),
        MotorcycleSpec(
            id = "mt07",
            brand = "Yamaha",
            model = "MT-07 (FZ-07)",
            modelYear = 2024,
            category = "Hyper Naked",
            engineType = "Liquid-cooled 4-stroke DOHC 8-valve 270° Crossplane CP2 Parallel-Twin",
            engineDisplacement = "689 cc",
            transmission = "Constant mesh 6-speed with multiplate wet clutch",
            fuelSystem = "Electronic fuel injection with dual Mikuni 38mm throttle bodies",
            coolingSystem = "Liquid-cooled with high-flow water pump and thermo-switch",
            frontBrake = "Dual 298mm floating hydraulic discs with 4-piston calipers, ABS",
            rearBrake = "Single 245mm hydraulic disc with single-piston Nissin caliper, ABS",
            frontSuspension = "41mm conventional telescopic fork, 130mm travel",
            rearSuspension = "Horizontally mounted link-type monocross shock with preload & rebound damping",
            tyreSizes = "Front: 120/70ZR17 (58W) | Rear: 180/55ZR17 (73W)",
            fuelCapacity = "14.0 Litres",
            weight = "184 kg (wet)",
            dimensions = "2,085 mm x 780 mm x 1,105 mm | Seat height: 805 mm | Wheelbase: 1,400 mm",
            oilCapacity = "2.6 Litres (with filter change) - Yamalube 10W-40 4T",
            serviceIntervalKm = 5000,
            chainServiceIntervalKm = 800,
            isDemoData = true
        ),
        MotorcycleSpec(
            id = "ninja400",
            brand = "Kawasaki",
            model = "Ninja 400 / Z400",
            modelYear = 2023,
            category = "Lightweight Supersport",
            engineType = "Liquid-cooled 4-stroke DOHC 8-valve Parallel-Twin",
            engineDisplacement = "399 cc",
            transmission = "6-speed return shift with Assist & Slipper clutch",
            fuelSystem = "DFI with dual 32mm throttle bodies",
            coolingSystem = "Liquid-cooled with radiator fan",
            frontBrake = "Single 310mm semi-floating petal disc with dual-piston caliper, ABS",
            rearBrake = "Single 220mm petal disc with dual-piston caliper, ABS",
            frontSuspension = "41mm telescopic front fork, 120mm travel",
            rearSuspension = "Bottom-link Uni-Trak with 5-way adjustable preload gas-charged shock",
            tyreSizes = "Front: 110/70R17 M/C 54H | Rear: 150/60R17 M/C 66H",
            fuelCapacity = "14.0 Litres",
            weight = "168 kg (curb weight)",
            dimensions = "1,990 mm x 710 mm x 1,120 mm | Seat height: 785 mm | Wheelbase: 1,370 mm",
            oilCapacity = "2.0 Litres (with filter) - SAE 10W-40",
            serviceIntervalKm = 6000,
            chainServiceIntervalKm = 600,
            isDemoData = true
        ),
        MotorcycleSpec(
            id = "r1250gs",
            brand = "BMW",
            model = "R 1250 GS Adventure",
            modelYear = 2023,
            category = "Adventure Touring",
            engineType = "Air/liquid-cooled 4-stroke Flat-Twin Boxer with BMW ShiftCam variable valve timing",
            engineDisplacement = "1,254 cc",
            transmission = "Constant mesh 6-speed with anti-hopping slipper clutch, enclosed shaft drive",
            fuelSystem = "Electronic intake pipe injection with 52mm throttle valve",
            coolingSystem = "Precision liquid/air cooling with dual front radiators",
            frontBrake = "Dual 305mm floating discs with radial 4-piston calipers, BMW Motorrad Integral ABS Pro",
            rearBrake = "Single 276mm disc with 2-piston floating caliper",
            frontSuspension = "BMW Telelever 37mm stanchion with central spring strut, Dynamic ESA",
            rearSuspension = "Cast aluminium single-sided swingarm with BMW Paralever, WAD strut, Dynamic ESA",
            tyreSizes = "Front: 120/70 R 19 | Rear: 170/60 R 17 (tubeless cross-spoke wheels)",
            fuelCapacity = "30.0 Litres (Adventure long-range tank)",
            weight = "268 kg (road ready, wet)",
            dimensions = "2,270 mm x 980 mm x 1,460 mm | Seat height: 890 / 910 mm",
            oilCapacity = "4.0 Litres (SAE 5W-40 API SL / JASO MA2)",
            serviceIntervalKm = 10000,
            chainServiceIntervalKm = 0, // Shaft drive!
            isDemoData = true
        ),
        MotorcycleSpec(
            id = "hunter350",
            brand = "Royal Enfield",
            model = "Hunter 350",
            modelYear = 2024,
            category = "Urban Roadster",
            engineType = "Single-cylinder 4-stroke SOHC Air-Oil cooled J-Series engine",
            engineDisplacement = "349 cc",
            transmission = "5-speed constant mesh with wet multi-plate clutch",
            fuelSystem = "Electronic Fuel Injection (EFI)",
            coolingSystem = "Air and Oil cooled with front radiator oil-cooler",
            frontBrake = "300mm disc with twin-piston floating caliper, dual-channel ABS",
            rearBrake = "270mm disc with single-piston floating caliper, dual-channel ABS",
            frontSuspension = "41mm telescopic front forks, 130mm travel",
            rearSuspension = "Twin tube emulsion shock absorbers with 6-step adjustable preload",
            tyreSizes = "Front: 110/70-17 54P (tubeless) | Rear: 140/70-17 66P (tubeless)",
            fuelCapacity = "13.0 Litres",
            weight = "181 kg (kerb weight)",
            dimensions = "2,055 mm x 800 mm x 1,055 mm | Seat height: 790 mm | Wheelbase: 1,370 mm",
            oilCapacity = "2.1 Litres - SAE 15W-50 Semi-synthetic API SL JASO MA2",
            serviceIntervalKm = 5000,
            chainServiceIntervalKm = 500,
            isDemoData = true
        )
    )

    val motorcycleStages = listOf(
        StageInfo(
            stageNumber = 1,
            title = "STAGE 1 — NEW MOTORCYCLE",
            subtitle = "Delivery, initial setup, & first checks",
            iconName = "FiberNew",
            keyItems = listOf(
                "Pre-delivery inspection (PDI checklist)",
                "Initial setup & mirror/lever ergonomics",
                "Tyre pressure verification (cold)",
                "Engine oil sight glass inspection",
                "Brake lever & pedal free-play check",
                "Drive chain tension (slack 25-35mm) & lubrication",
                "Battery voltage & terminal tightness",
                "All lights (high/low, indicators, brake lights, horn)",
                "Throttle return & clutch friction point check",
                "Basic riding precautions & owner's manual reading"
            ),
            fullExplanation = """
Before riding your brand-new motorcycle from the showroom, perform a methodical walkaround.
1. Pre-Delivery Inspection: Confirm no shipping fluids or coolant residues are leaking. Ensure all fairing fasteners, axle pinch bolts, and footpeg brackets are torqued correctly.
2. Tyres: New tyres have mold-release lubricants and zero heat cycles. Ride conservatively for the first 100-150 km to scuff the tread surface across all lean angles. Check cold tyre pressure before every morning ride.
3. Engine Oil: Always check the oil sight glass or dipstick while holding the motorcycle vertical on level ground (never on the side stand unless the manual specifies).
4. Controls: Adjust brake and clutch levers to align naturally with your wrists and forearms. Check throttle snap-back with handlebars turned to full left and full right lock.
5. Chain: Inspect factory chain lubrication. New chains must have adequate slack (typically 25-35 mm measured at the middle of the bottom run).
6. Owner's Manual: Keep the manual under the seat or downloaded on your phone. It contains the exact tyre pressures, torque specs, and fuse ratings.
            """.trimIndent(),
            diyFriendly = true,
            manufacturerNotice = "Always follow the motorcycle manufacturer's official service schedule."
        ),
        StageInfo(
            stageNumber = 2,
            title = "STAGE 2 — RUN-IN / BREAK-IN PERIOD",
            subtitle = "Mating internal engine components smoothly",
            iconName = "Speed",
            keyItems = listOf(
                "What engine break-in means (piston ring seating)",
                "Recommended riding behaviour: varied RPMs & engine braking",
                "Avoiding prolonged constant throttle or unnecessary redline",
                "Smooth throttle roll-on and progressive deceleration",
                "Frequent gear shifts across all ratios",
                "Engine temperature management: avoid overheating in traffic",
                "Initial fastener re-torque & leak monitoring",
                "Planning for the critical first service (800 - 1,000 km)"
            ),
            fullExplanation = """
The engine break-in (run-in) period is crucial for your motorcycle's long-term horsepower, oil consumption, and longevity.
1. What break-in means: Piston rings must expand against cylinder walls to create a tight microscopic seal. Valve faces seat into cylinder heads, and transmission gear teeth hone against each other.
2. Riding Behavior: Vary your engine speed! Do NOT ride at a fixed speed on long highway stretches. Use curvy back roads that require regular shifting, progressive throttle opening, and gentle engine braking (which generates intake vacuum to draw lubricant over the top rings).
3. Throttle & RPM: Avoid high RPM redlines and never lug the engine (full throttle in too high a gear at low RPM). Smooth, progressive acceleration is key.
4. Warm Up: Let the engine idle for 45-60 seconds to circulate oil to the camshafts before riding gently until normal operating temperature is reached.
5. First Inspection: Watch for any weeping gaskets or loose mirror stalks caused by initial settling vibrations.
            """.trimIndent(),
            diyFriendly = true,
            manufacturerNotice = "Do not exceed manufacturer-specified break-in RPM limits verified from the official owner's manual."
        ),
        StageInfo(
            stageNumber = 3,
            title = "STAGE 3 — FIRST SERVICE (800 - 1,000 KM)",
            subtitle = "Clearing metallic break-in debris & fine-tuning",
            iconName = "BuildCircle",
            keyItems = listOf(
                "Engine oil replacement (removes abrasive machining shavings)",
                "Oil filter replacement (mandatory during first service)",
                "Drive chain tension inspection & deep lubrication",
                "Brake system inspection (fluid level, pad bedding, lever feel)",
                "Tyre wear pattern & cold pressure calibration",
                "Chassis fastener torque inspection (axles, triple trees, engine mounts)",
                "Battery charge rate & terminal tightness",
                "ECU diagnostic scan for stored trouble codes",
                "Throttle play & clutch cable free play adjustment",
                "General inspection of cooling hoses & suspension seals"
            ),
            fullExplanation = """
The first service (usually at 1,000 km / 600 miles or 1 month) is the single most important service of your motorcycle's life.
1. Oil & Filter: The factory fill oil collects metallic micro-particles, clutch composite dust, and assembly lube. Flushing this oil and replacing the filter prevents premature bearing wear.
2. Fastener Torque: As metals undergo heat-expansion cycles and road vibration, fasteners settle. Mechanics check critical torque points: engine hanger bolts, swingarm pivot, caliper mounting bolts, and steering stem pinch bolts.
3. Chain Slack: New chains experience initial 'stretch' (pin and bushing settling). The chain must be adjusted back to factory tolerance and re-lubricated.
4. Clutch & Throttle: New cables stretch slightly during early use; re-establishing 2-3mm of lever free-play ensures full clutch engagement and avoids clutch slippage.
            """.trimIndent(),
            diyFriendly = false,
            manufacturerNotice = "First service by an authorized workshop is strongly recommended to preserve factory warranty."
        ),
        StageInfo(
            stageNumber = 4,
            title = "STAGE 4 — REGULAR SERVICE INTERVALS",
            subtitle = "Scheduled maintenance by time & mileage",
            iconName = "CalendarMonth",
            keyItems = listOf(
                "Intervals based on both Kilometres AND Months (whichever comes first)",
                "Oil breakdown occurs from heat, fuel dilution, and time",
                "Manufacturer-specific service matrix follow-up",
                "Visual safety checklist before every service interval",
                "Record-keeping for warranty preservation and resale value"
            ),
            fullExplanation = """
Motorcycles require frequent servicing compared to cars due to higher engine RPMs, smaller oil capacities, and wet clutches that share oil with the gearbox.
- Intervals: Typically every 5,000 km to 10,000 km or every 6 to 12 months.
- Even if you did not ride the full distance, engine oil absorbs atmospheric moisture and combustion by-products, causing acid formation and additive breakdown.
- Regular service ensures early detection of wearing items before they cause unexpected on-road failure.
            """.trimIndent(),
            diyFriendly = true,
            manufacturerNotice = "Always follow the motorcycle manufacturer's official service schedule."
        ),
        StageInfo(
            stageNumber = 5,
            title = "STAGE 5 — PERIODIC MAINTENANCE",
            subtitle = "Replacing wear items and renewing vital fluids",
            iconName = "Tune",
            keyItems = listOf(
                "Engine oil & oil filter replacement (every 5k-10k km / 12 mo)",
                "Air filter inspection & replacement (every 12k-18k km)",
                "Spark plug replacement / gap check (standard or Iridium)",
                "Brake pads wear inspection (replace if under 1.5mm material)",
                "Brake fluid flush & bleed (DOT 4 / DOT 5.1 every 2 years)",
                "Coolant replacement (ethylene glycol 50/50 every 2-3 years)",
                "Drive chain deep cleaning, slack adjustment, & sprocket tooth wear check",
                "Clutch free play & cable lubrication",
                "Tyre tread depth (minimum 1.5 - 2.0 mm) and age check (under 5 years)",
                "Battery health test, suspension fork wiper seal inspection"
            ),
            fullExplanation = """
Periodic maintenance maintains your motorcycle at peak factory operating standards.
- Brake Fluid: Highly hygroscopic (absorbs water vapor through microscopic rubber pores). Over time, this drops the fluid boiling point, leading to brake fade or a spongy lever under heavy mountain braking.
- Spark Plugs: Electrode wear widens the gap, straining the ignition coils and causing rough idle or poor fuel economy.
- Air Filter: A clogged filter creates an over-rich fuel mixture, robbing horsepower and increasing carbon deposits on valves.
- Coolant: Corrosion inhibitors deplete over time, leaving aluminum water jackets vulnerable to internal galvanic corrosion.
            """.trimIndent(),
            diyFriendly = true,
            manufacturerNotice = "Use genuine or OEM-certified fluids and replacement parts."
        ),
        StageInfo(
            stageNumber = 6,
            title = "STAGE 6 — HIGH-MILEAGE MAINTENANCE",
            subtitle = "Deep mechanical inspections as odometer increases",
            iconName = "AltRoute",
            keyItems = listOf(
                "Valve clearance inspection & shim adjustment (critical every 24k-42k km)",
                "Wheel bearings inspection (spin test for roughness or play)",
                "Steering head stem bearings (notchiness, detents, or looseness)",
                "Suspension fork oil flush, bushing inspection, & seal renewal",
                "Rear shock absorber dampening performance & linkage bearing regrease",
                "Chain and both sprockets replacement as a matched set",
                "Brake master cylinder & caliper piston seal overhaul",
                "Cooling system hose elasticity & water pump mechanical seal check",
                "Electrical system stator output (AC voltage) & regulator DC voltage check"
            ),
            fullExplanation = """
As your motorcycle crosses 25,000 to 50,000+ km, components that operate quietly inside require scheduled deep inspection.
- Valve Clearances: As valves hammer into their hardened seats, clearances often tighten. Tight valves do not fully seal, overheating the valve face and risking burned valves or catastrophic loss of compression.
- Steering Head Bearings: Constant straight-line riding and hard braking create indentations (brinelling) in the bearing races, leading to dangerous wobble or a 'notchy' handlebar feel.
- Fork Oil: Hydraulic fork oil shears and turns into black metallic sludge from internal slider friction, compromising front-end damping, dive control, and tyre grip.
- Swingarm & Shock Linkage: Need disassembly, cleaning, and repacking with waterproof lithium grease.
            """.trimIndent(),
            diyFriendly = false,
            manufacturerNotice = "Valve clearance and internal suspension work should be executed by qualified technicians with factory service manuals."
        ),
        StageInfo(
            stageNumber = 7,
            title = "STAGE 7 — LONG-TERM OWNERSHIP",
            subtitle = "Preventive care, corrosion control, & asset longevity",
            iconName = "Verified",
            keyItems = listOf(
                "Comprehensive corrosion prevention (anti-corrosion sprays on fasteners)",
                "Battery tender maintenance (smart trickle charger when parked)",
                "Tyre dry-rot & compound aging replacement (maximum 5-6 years lifespan)",
                "Brake line inspection (replace rubber lines every 4-5 years or upgrade to braided)",
                "Suspension health & chassis alignment checks",
                "Engine health: compression test & oil consumption logging",
                "Electrical health: cleaning grounds, waterproof grease on connectors",
                "Complete service documentation & receipt archiving"
            ),
            fullExplanation = """
Keeping a motorcycle running like new for 10+ years is rewarding and preserves its value.
- Corrosion Protection: Wash off road salt and grime immediately. Apply protective corrosion inhibitors (such as ACF-50 or XCP) to engine cases, frame welds, and exposed fasteners.
- Rubber Components: Rubber hoses, intake boots, and fuel lines dry out and crack over time due to ozone and UV exposure. Inspect for stiffness or hairline fissures.
- Battery Tender: Lead-acid and AGM batteries sulfatize when stored below 12.4V. Connecting a smart maintenance charger extends battery life to 4-6 years.
- Documentation: A full paper or digital service trail with mileage-stamped receipts yields significantly higher resale value.
            """.trimIndent(),
            diyFriendly = true,
            manufacturerNotice = "Inspect critical rubber brake lines every season for swelling or cracking."
        ),
        StageInfo(
            stageNumber = 8,
            title = "STAGE 8 — STORAGE (SHORT & LONG-TERM)",
            subtitle = "Winterizing and off-season vehicle preservation",
            iconName = "House",
            keyItems = listOf(
                "Short-term storage (under 1 month) vs Long-term storage (1-6+ months)",
                "Fuel system: fill tank 100% full + add fuel stabilizer (prevents condensation)",
                "Battery: remove from bike or connect to smart tender",
                "Tyres: elevate on paddock stands to prevent flat-spotting; set to max cold PSI",
                "Engine: change oil before storage (acidic used oil corrodes bearings)",
                "Exhaust & Intake: plug openings to keep rodents and nesting insects out",
                "Moisture protection & breathable motorcycle cover (avoid moisture trap tarps)",
                "Cleaning & waxing: wash, dry thoroughly, and apply protective wax coat",
                "Periodic inspection during storage without short engine starts"
            ),
            fullExplanation = """
Improper storage is one of the quickest ways to damage a motorcycle.
1. Fuel Care: Ethanol in modern gasoline absorbs ambient moisture and separates (phase separation) within 60 days, gumming up injectors and rusting steel fuel tanks. Fill the tank completely to minimize air volume, add quality fuel stabilizer, and run the engine for 5 minutes so treated fuel reaches the fuel rail.
2. Fresh Oil: Combustion acids in old oil will etch internal engine bearings while sitting motionless. Always store with fresh oil.
3. Tyres: Place the motorcycle on center stand or front/rear paddock stands to remove weight from the tyre carcass. If stored on ground, inflate to 3-4 PSI above normal and roll the bike forward every few weeks.
4. DO NOT start the bike for '5 minutes every week': Short idling never reaches full oil operating temperature, creating water condensation inside the crankcase and exhaust that rots the muffler from the inside out!
            """.trimIndent(),
            diyFriendly = true,
            manufacturerNotice = "Store only in dry, well-ventilated areas away from open pilot lights or electric motors."
        ),
        StageInfo(
            stageNumber = 9,
            title = "STAGE 9 — RESTORATION & REBUILD",
            subtitle = "Bringing classics back to life safely",
            iconName = "Handyman",
            keyItems = listOf(
                "Comprehensive pre-restoration inspection & safety assessment",
                "Frame & chassis alignment (straightness, cracks, rust perforation)",
                "Engine inspection: compression test, leak-down test, cylinder bore measurement",
                "Electrical harness inspection: brittle wiring, corroded terminals, fuse boxes",
                "Fuel tank de-rusting & ultrasonic injector / carburetor cleaning",
                "Full brake overhaul: rebuilt master cylinders, fresh stainless pistons, new braided lines",
                "Suspension rebuild: re-valving, fresh seals, swingarm needle bearing replacement",
                "Wheel truing, new rim bands, wheel bearings, and fresh rubber",
                "Cosmetic restoration: vapour blasting, powder coating, paint & clear coat",
                "Clear separation: DIY beginner tasks vs Professional workshop specialist jobs"
            ),
            fullExplanation = """
Restoring an older, barn-find, or neglected motorcycle requires a systematic safety-first roadmap.
- DIY Beginner Tasks: Cosmetic detailing, replacement of mirrors/levers/cables, air and oil filter replacement, chain and sprocket installation, bulb replacement, bleeding brakes.
- Specialist Workshop Jobs: Engine bottom-end crankshaft rebuilding, cylinder boring and honing, valve seat recutting, frame straightening after accidents, electronic harness rewiring, and dyno tuning.
- Safety Alert: Never attempt to ride a revived bike without rebuilding the braking system and installing fresh tyres regardless of remaining tread depth!
            """.trimIndent(),
            diyFriendly = false,
            manufacturerNotice = "Frame structural welding or engine bottom-end rebuilding must always be performed by certified specialists."
        )
    )

    val motorcycleParts = listOf(
        // ENGINE
        MotorcyclePart(
            id = "p_cylinder",
            name = "Cylinder & Cylinder Block",
            system = ComponentSystem.ENGINE,
            location = "Central engine block above crankcase",
            function = "Contains the combustion chambers where fuel burns to drive pistons downward.",
            beginnerExplanation = "The cylinder is the metal tube where the engine's power is created. The piston slides up and down inside it thousands of times every minute.",
            intermediateExplanation = "Motorcycle cylinders are typically cast from aluminum alloy with hard, low-friction composite coatings (such as Nikasil) or pressed cast-iron liners to dissipate combustion heat quickly.",
            advancedExplanation = "Bore geometry is honed with cross-hatch micro-grooves that retain lubricating oil. Proper piston-to-cylinder clearance (often 0.03-0.05 mm) is essential to prevent piston slap when cold and seizure when hot.",
            maintenance = "Inspect compression and leak-down percentage every 20,000 km. Maintain clean coolant and quality motor oil.",
            commonProblems = listOf("Cylinder wall scoring from dirt ingestion", "Glazing from improper break-in", "Warped cylinder head mating surface from overheating"),
            warningSigns = listOf("Blue exhaust smoke (burning oil)", "Loss of engine compression", "Milky oil from blown head gasket"),
            replacementConsiderations = "Re-boring and fitting oversized pistons (on iron liners) or re-plating Nikasil coating when bore taper exceeds factory service limit."
        ),
        MotorcyclePart(
            id = "p_piston",
            name = "Piston & Piston Rings",
            system = ComponentSystem.ENGINE,
            location = "Inside each cylinder bore",
            function = "Transfers the force of exploding fuel-air mixture to the connecting rod and crankshaft.",
            beginnerExplanation = "The piston acts like a plug moving up and down. Rings around it keep the exploding gases trapped above while scraping oil away below.",
            intermediateExplanation = "Pistons feature top compression ring, second scraper ring, and a three-piece oil control ring pack. Piston crowns are contoured to optimize flame propagation.",
            advancedExplanation = "Forged aluminum pistons offer superior tensile strength and fatigue resistance in high-RPM engines compared to cast pistons, but require slightly looser cold clearance due to higher thermal expansion.",
            maintenance = "Strict adherence to oil change intervals. Avoid engine pinging/knocking by using recommended fuel octane.",
            commonProblems = listOf("Piston ring sticking from carbon buildup", "Detonation damage on piston crown", "Skirt scuffing from oil starvation"),
            warningSigns = listOf("Excessive blow-by pressure in crankcase", "Metallic ticking that changes with engine load", "High oil consumption"),
            replacementConsiderations = "Always replace piston rings, wrist pins, and circlips whenever cylinders are removed."
        ),
        MotorcyclePart(
            id = "p_valves",
            name = "Engine Valves & Camshaft",
            system = ComponentSystem.ENGINE,
            location = "Cylinder head above combustion chambers",
            function = "Opens and closes intake and exhaust ports in precise synchrony with engine rotation.",
            beginnerExplanation = "Valves are metal doors that open to let fresh air and fuel in, and let burnt exhaust smoke out.",
            intermediateExplanation = "Intake valves are larger to maximize air volume; exhaust valves are forged from heat-resistant alloys. Camshaft lobes push on shim buckets or rocker arms to actuate valves.",
            advancedExplanation = "DOHC (Double Overhead Cam) designs separate intake and exhaust camshafts for independent lobe separation angle and duration. Valve overlap scavenging is tuned for peak volumetric efficiency at target RPM.",
            maintenance = "Check valve clearances according to manufacturer schedule (typically every 24,000 to 42,000 km). Tight clearances must be corrected with replacement shims.",
            commonProblems = listOf("Recessed valve seats causing clearance to tighten", "Carbon buildup on intake valve backs", "Worn valve stem seals"),
            warningSigns = listOf("Hard starting when engine is warm/hot", "Unstable idle speed", "Backfiring through airbox or exhaust"),
            replacementConsiderations = "Inspect valve stem runout and tip wear. Replace valve stem oil seals whenever springs are compressed."
        ),
        MotorcyclePart(
            id = "p_spark_plug",
            name = "Spark Plug & Ignition Coil",
            system = ComponentSystem.ENGINE,
            location = "Threaded into the top or side of the cylinder head",
            function = "Generates a 20,000+ Volt electrical arc that ignites the compressed air-fuel mixture.",
            beginnerExplanation = "The spark plug creates the tiny lightning bolt that lights the fuel fire inside the engine at the exact right microsecond.",
            intermediateExplanation = "Available in standard nickel, platinum, or fine-wire Iridium electrodes. Heat range rating dictates how quickly the ceramic insulator dissipates heat to the cylinder head.",
            advancedExplanation = "Iridium tips (0.4-0.6mm diameter) concentrate electrical field intensity, requiring less voltage from the coil-on-plug (stick coil) to fire consistently under high cylinder pressures.",
            maintenance = "Check electrode gap and color every 10,000 km. Replace standard copper plugs at 10,000-15,000 km, Iridium plugs at 30,000-40,000 km.",
            commonProblems = listOf("Carbon fouling from short trips/rich mixture", "Electrode erosion", "Cracked ceramic insulator causing misfire"),
            warningSigns = listOf("Engine misfire / stuttering under acceleration", "Rough idle", "Difficult cold starting"),
            replacementConsiderations = "Always torque to manufacturer spec (usually 10-15 Nm); never cross-thread into soft aluminum head threads."
        ),
        MotorcyclePart(
            id = "p_engine_oil",
            name = "Engine Oil & Lubrication System",
            system = ComponentSystem.ENGINE,
            location = "Engine sump, oil pump, internal oil galleries, & filter",
            function = "Lubricates, cools, cleans, and seals the engine, transmission gears, and wet clutch.",
            beginnerExplanation = "Motorcycle oil is the lifeblood of the machine. Unlike cars, motorcycle oil lubricates the engine, transmission gears, and clutch all in one shared pool.",
            intermediateExplanation = "Must comply with JASO MA or MA2 motorcycle standards (low friction modifiers to prevent wet clutch slippage). Viscosity ratings (e.g. 10W-40) ensure cold flow and hot film thickness.",
            advancedExplanation = "High shear forces between gearbox gear teeth break down polymer viscosity index improvers. Oil also cools piston undersides via pressurized cooling oil jets.",
            maintenance = "Change oil and filter every 5,000-10,000 km or annually. Check sight glass before every ride.",
            commonProblems = listOf("Low oil level causing bearing seizure", "Oil dilution with unburnt gasoline", "Using automotive car oil causing clutch slipping"),
            warningSigns = listOf("Oil pressure warning light on dashboard", "Excessive mechanical clatter", "Difficulty finding neutral / stiff shifting"),
            replacementConsiderations = "Always install a new crush washer on the drain bolt and pre-lube the oil filter rubber gasket."
        ),

        // TRANSMISSION & CLUTCH
        MotorcyclePart(
            id = "p_clutch",
            name = "Multi-Plate Wet Clutch & Slipper Unit",
            system = ComponentSystem.TRANSMISSION,
            location = "Right side of engine behind clutch cover",
            function = "Connects and disconnects engine power from the transmission to allow stopping and gear changes.",
            beginnerExplanation = "The clutch lets you start moving smoothly and change gears. Pulling the lever disconnects the engine from the rear wheel.",
            intermediateExplanation = "Consists of alternating friction plates (fiber coated) and steel drive plates bathed in motor oil. Slipper clutches feature internal ramps that slip slightly during aggressive downshifts to prevent rear wheel hop.",
            advancedExplanation = "Assist and slipper mechanisms use torque-reaction cams: under acceleration, ramps wedge plates together for firmer lockup with lighter clutch springs; under deceleration, ramps disengage to slip plates.",
            maintenance = "Maintain 2-3 mm of free play at the clutch lever perch. Lubricate the clutch cable regularly.",
            commonProblems = listOf("Clutch slip under full throttle", "Clutch drag (bike creeps forward in gear with lever pulled)", "Glazed or warped friction plates"),
            warningSigns = listOf("Engine RPM climbs fast but bike does not accelerate proportionally", "Hard to shift into neutral at a stop"),
            replacementConsiderations = "Soak new friction plates in fresh engine oil for at least 30-60 minutes prior to installation."
        ),
        MotorcyclePart(
            id = "p_gearbox",
            name = "Sequential Gearbox & Shift Mechanism",
            system = ComponentSystem.TRANSMISSION,
            location = "Inside lower crankcase behind engine crankshaft",
            function = "Multiplies engine torque and provides multiple speed ratios (usually 1-N-2-3-4-5-6).",
            beginnerExplanation = "The gearbox allows the motorcycle to accelerate from a standstill with strong pulling power and cruise on highways at calm engine speeds.",
            intermediateExplanation = "Constant-mesh sequential transmission using shift drum, selector forks, and dog-ring engagement rather than automotive synchromesh.",
            advancedExplanation = "Gears are splined on input (main) and output (countershaft) shafts. Shift drum rotates in indexed steps via shift star and detent arm, moving selector forks laterally to mate sliding dogs.",
            maintenance = "Frequent clean oil changes prevent gear dog rounding. Ensure shift linkage ball joints are tight and lubricated.",
            commonProblems = listOf("False neutrals between gears", "Popping out of gear under load (rounded dogs)", "Bent shift fork from aggressive kick-shifting"),
            warningSigns = listOf("Grinding or violent clunking during shifts", "Inability to select higher or lower gears"),
            replacementConsiderations = "Requires complete crankcase splitting to inspect or rebuild."
        ),

        // BRAKES
        MotorcyclePart(
            id = "p_brake_caliper_pads",
            name = "Brake Calipers & Brake Pads",
            system = ComponentSystem.BRAKES,
            location = "Mounted on fork bottoms and rear swingarm over discs",
            function = "Squeezes high-friction pads against revolving brake rotors to convert kinetic energy into heat.",
            beginnerExplanation = "Brake calipers and pads are your motorcycle's primary safety system. When you squeeze the lever, hydraulic fluid pushes the pads against the rotor to stop you.",
            intermediateExplanation = "Pads come in Sintered (metallic particles fused under heat for strong wet/dry bite) or Organic (quieter, softer bite). Calipers feature 2 to 4 opposing hydraulic pistons.",
            advancedExplanation = "Radial-mount calipers bolt directly along the radius of the disc rather than axially, minimizing caliper flex under violent braking for precise lever feedback.",
            maintenance = "Inspect pad thickness monthly (replace when friction material reaches 1.5 - 2.0 mm). Clean brake dust from caliper pistons with brake cleaner.",
            commonProblems = listOf("Piston sticking from brake dust and road grime", "Uneven pad wear", "Brake squeal from glazed pads"),
            warningSigns = listOf("Spongy lever feel", "Scraping metal-on-metal sound", "Motorcycle pulls or brakes drag when released"),
            replacementConsiderations = "Replace pads in full axle pairs. Never contaminate new pads with grease or brake fluid."
        ),
        MotorcyclePart(
            id = "p_brake_fluid",
            name = "Brake Fluid & Master Cylinder",
            system = ComponentSystem.BRAKES,
            location = "Handlebar reservoir (front) and under frame side cover (rear)",
            function = "Transmits non-compressible hydraulic pressure from hand lever / foot pedal to caliper pistons.",
            beginnerExplanation = "The brake fluid carries your hand's squeeze pressure through hoses straight down to the brake calipers.",
            intermediateExplanation = "DOT 4 or DOT 5.1 glycol-based fluid. Glycol absorbs atmospheric moisture over time, lowering the boiling point.",
            advancedExplanation = "Water contamination causes fluid vaporization into compressible steam under repeated hard braking, resulting in total sudden brake lever collapse (vapor lock).",
            maintenance = "Flush and bleed brake lines with fresh fluid every 2 years or sooner if dark amber/brown.",
            commonProblems = listOf("Moisture absorption / dark oxidized fluid", "Air bubbles in line causing soft lever", "Corrosion inside master cylinder bore"),
            warningSigns = listOf("Lever pulls all the way to the handlebar grip", "Loss of braking power after downhill passes"),
            replacementConsiderations = "Brake fluid damages motorcycle paintwork instantly—protect all painted surfaces with rags during bleeding."
        ),

        // SUSPENSION
        MotorcyclePart(
            id = "p_forks",
            name = "Front Suspension Forks (USD / Conventional)",
            system = ComponentSystem.SUSPENSION,
            location = "Front wheel steering assembly held by triple clamps",
            function = "Absorbs bumps, maintains front tyre traction, and stabilizes motorcycle geometry during braking.",
            beginnerExplanation = "Front forks act like shock absorbers for your front wheel, keeping the bike stable and comfortable over bumps and during hard braking.",
            intermediateExplanation = "Contains coil springs to support vehicle weight and internal damping oil/valving cartridges to control the speed of compression and rebound.",
            advancedExplanation = "Inverted (USD - Upside Down) forks place the larger, stiffer outer tubes in the high-stress triple clamp area, drastically reducing chassis flex during trail braking.",
            maintenance = "Inspect fork chrome stanchions for stone chips and wipe dust off wiper seals after every wash. Replace fork oil and dust/oil seals every 20,000-30,000 km.",
            commonProblems = listOf("Blown oil seal leaking fluid down the fork leg", "Loss of rebound damping (bouncy front end)", "Pitted chrome causing rapid seal destruction"),
            warningSigns = listOf("Oil ring residue on shiny fork tube", "Oil dripping onto front brake calipers (EXTREMELY DANGEROUS)", "Front end dives excessively during gentle braking"),
            replacementConsiderations = "Always change fork seals and slider bushings as matched pairs on both legs simultaneously."
        ),
        MotorcyclePart(
            id = "p_rear_shock",
            name = "Rear Monoshock & Swingarm Linkage",
            system = ComponentSystem.SUSPENSION,
            location = "Connecting swingarm to main motorcycle frame",
            function = "Controls rear wheel travel, maintains drive traction under acceleration, and cushions rider.",
            beginnerExplanation = "The rear shock controls the back wheel over potholes and keeps the rear tyre planted when you open the throttle.",
            intermediateExplanation = "Features nitrogen gas-charged pressurized chamber, spring preload adjustment, and rebound damping clickers.",
            advancedExplanation = "Progression linkage converts linear swingarm pivot movement into increasing shock resistance near end-of-travel to prevent bottoming out.",
            maintenance = "Check preload sag (rider sag ~30mm). Clean and grease swingarm needle bearings every 25,000 km.",
            commonProblems = listOf("Blown seal leaking hydraulic damping fluid", "Seized linkage bearings from pressure washing", "Sagging worn-out spring"),
            warningSigns = listOf("Motorcycle pogo-sticks / wallows through corners", "Harsh metal bottoming out over speed humps"),
            replacementConsiderations = "Most factory OEM shocks are non-rebuildable sealed units and must be replaced or upgraded to aftermarket rebuildable units."
        ),

        // DRIVETRAIN
        MotorcyclePart(
            id = "p_chain_sprocket",
            name = "Drive Chain & Sprockets",
            system = ComponentSystem.DRIVETRAIN,
            location = "Left side connecting gearbox output shaft to rear wheel hub",
            function = "Transfers rotational power from engine gearbox directly to the rear wheel.",
            beginnerExplanation = "The chain and sprockets connect the engine to the back tyre. It operates in the open air, so it requires frequent cleaning and lubricating.",
            intermediateExplanation = "O-ring or X-ring chains contain internal grease sealed inside link pins by miniature rubber elastomeric rings. Sprockets feature hardened steel or alloy teeth.",
            advancedExplanation = "Chain pitch (e.g. 520, 525, 530) designates link pin spacing and inner roller width. A worn chain develops pitch elongation ('stretch'), which accelerates sprocket tooth hooking.",
            maintenance = "Clean with kerosene or dedicated chain cleaner, then lubricate every 500-800 km and immediately after riding in rain. Adjust slack to 25-35mm.",
            commonProblems = listOf("Tight spots and frozen/kinked links", "Excessive slack causing chain slap against swingarm", "Shark-fin hooked sprocket teeth"),
            warningSigns = listOf("Rattling or slapping noise on deceleration", "Chain pulls more than halfway off rear sprocket teeth", "Rust flakes on side plates"),
            replacementConsiderations = "ALWAYS replace front sprocket, rear sprocket, and chain together as a matched trio."
        ),

        // ELECTRICAL
        MotorcyclePart(
            id = "p_battery",
            name = "12V Motorcycle Battery (AGM / Lithium)",
            system = ComponentSystem.ELECTRICAL,
            location = "Under the rider seat or behind fuel tank side cover",
            function = "Powers starter motor, fuel pump, ignition system, ECU, and motorcycle lighting.",
            beginnerExplanation = "The battery stores electricity to start the engine and keep safety electronics running before the alternator takes over.",
            intermediateExplanation = "Absorbed Glass Mat (AGM) sealed lead-acid or Lithium Iron Phosphate (LiFePO4). A healthy resting battery shows 12.6V - 12.8V DC.",
            advancedExplanation = "High cranking amps (CCA) are needed to overcome the high compression of motorcycle engines without decompression valves.",
            maintenance = "Keep terminals clean and torqued. Connect to a smart battery tender if stored for more than 2-3 weeks.",
            commonProblems = listOf("Sulfation from deep discharge", "Corroded or loose terminal bolts", "Premature drain from parasitic alarm draw"),
            warningSigns = listOf("Rapid clicking sound when pressing start button", "Dash screen resets or dims during cranking", "Sluggish starter motor turn"),
            replacementConsiderations = "When installing Lithium batteries, verify that the motorcycle's charging regulator does not exceed 14.6V."
        ),
        MotorcyclePart(
            id = "p_stator_regulator",
            name = "Stator (Alternator) & Regulator/Rectifier",
            system = ComponentSystem.ELECTRICAL,
            location = "Stator inside left engine casing; Regulator mounted in cooling airflow",
            function = "Generates alternating electric current (AC) and converts it to regulated direct current (DC ~14.2V) to recharge battery.",
            beginnerExplanation = "The stator makes electricity from the spinning engine, and the regulator cleans and regulates it so your battery recharges while riding.",
            intermediateExplanation = "3-phase stator creates 30-70V AC depending on engine RPM. Regulator/Rectifier shunts excess voltage to ground as heat while rectifying AC to DC.",
            advancedExplanation = "MOSFET or series-type regulators run much cooler and place less continuous magnetic drag on the stator compared to older shunt-type SCR regulators.",
            maintenance = "Inspect connector plug between stator and regulator for melting or heat discoloration every 15,000 km.",
            commonProblems = listOf("Burned/shorted stator copper windings", "Overheating regulator causing battery boiling (over 15V)", "Blown rectifier diodes causing battery discharge"),
            warningSigns = listOf("Battery dies while riding even with a brand new battery", "Headlights flicker or blow out bulbs", "Engine stalls in heavy traffic"),
            replacementConsiderations = "Use a multimeter to measure AC voltage across all three yellow stator pin pairs (should be balanced)."
        ),

        // WHEELS & TYRES
        MotorcyclePart(
            id = "p_tyres",
            name = "Motorcycle Tyres & Valve Stems",
            system = ComponentSystem.WHEELS,
            location = "Front and rear wheels",
            function = "The only contact patches between the motorcycle and the road (each contact patch is roughly the size of a credit card).",
            beginnerExplanation = "Your tyres are your life. They provide grip for leaning in corners, stopping, and accelerating over dry and wet asphalt.",
            intermediateExplanation = "Dual-compound tyres utilize harder rubber in the center for touring mileage and softer high-silica rubber on shoulders for cornering grip.",
            advancedExplanation = "Tyre pressures affect carcass carcass deflection, internal temperature buildup, profile crown curvature, and trail geometry. Even 3-4 PSI under-inflation destabilizes steering input.",
            maintenance = "Check cold tyre pressure with an accurate gauge weekly. Measure tread depth across center and shoulders.",
            commonProblems = listOf("Center squaring from highway riding", "Scalloping / cupping from worn suspension or improper PSI", "Dry-rot micro-cracks from UV / age"),
            warningSigns = listOf("Vague or heavy steering into corners", "Loss of grip on painted road markings", "Tyre older than 5 years based on DOT date code"),
            replacementConsiderations = "Never exceed 5-6 years from tyre manufacture date (check 4-digit DOT code on sidewall). Replace valve stems with every tyre change."
        )
    )

    val troubleshootingCategories = listOf(
        TroubleshootingCategory(
            id = "wont_start",
            title = "Motorcycle Won't Start",
            icon = "PowerSettingsNew",
            rootStepId = "q_starter_turns",
            description = "Diagnose battery, safety switches, fuel delivery, or starter issues."
        ),
        TroubleshootingCategory(
            id = "overheating",
            title = "Engine Overheating",
            icon = "DeviceThermostat",
            rootStepId = "q_fan_running",
            description = "High coolant temperature, flashing warning lights, or boiling overflow."
        ),
        TroubleshootingCategory(
            id = "brake_problems",
            title = "Spongy / Weak Brakes",
            icon = "DoNotDisturbOn",
            rootStepId = "q_lever_to_bar",
            description = "Loss of braking firmness, lever bottoming out, or pulsing."
        ),
        TroubleshootingCategory(
            id = "engine_noise",
            title = "Unusual Engine Noise",
            icon = "Hearing",
            rootStepId = "q_noise_type",
            description = "Ticking, metallic knocking, or chain slapping sounds."
        ),
        TroubleshootingCategory(
            id = "gear_shifting",
            title = "Gear Shifting Difficulty",
            icon = "SettingsSuggest",
            rootStepId = "q_clutch_drag",
            description = "Hard shifting, false neutrals, or cannot find neutral."
        ),
        TroubleshootingCategory(
            id = "vibration",
            title = "Vibration / Wobble While Riding",
            icon = "SyncProblem",
            rootStepId = "q_wobble_speed",
            description = "Handlebar headshake, chassis vibration, or wobble in curves."
        )
    )

    val troubleshootingSteps = mapOf(
        // Won't start flow
        "q_starter_turns" to TroubleshootingStep(
            id = "q_starter_turns",
            question = "When you press the start button, does the starter motor turn the engine over?",
            contextInfo = "Listen closely when pressing the starter button with the ignition switch ON.",
            yesTargetId = "q_smell_fuel",
            noTargetId = "q_click_sound"
        ),
        "q_click_sound" to TroubleshootingStep(
            id = "q_click_sound",
            question = "Do you hear a rapid clicking sound or complete silence?",
            contextInfo = "Rapid clicking usually indicates a starter solenoid vibrating due to low battery voltage.",
            yesTargetId = "res_battery_low",
            noTargetId = "q_safety_switches"
        ),
        "res_battery_low" to TroubleshootingStep(
            id = "res_battery_low",
            question = "",
            conclusionTitle = "Battery Discharged or Loose Terminals",
            conclusionAdvice = "The starter solenoid clicks because the battery has enough voltage to trip the relay, but not enough current (CCA) to crank the starter motor. Actions:\n1. Check battery terminal bolts with a 10mm wrench for looseness.\n2. Measure resting voltage: if under 12.2V, charge on a motorcycle battery charger.\n3. Jump-start carefully using a 12V portable jump pack or car battery (with car engine OFF).",
            isHazardous = false
        ),
        "q_safety_switches" to TroubleshootingStep(
            id = "q_safety_switches",
            question = "Are all safety interlocks satisfied? (Engine kill switch set to RUN, bike in NEUTRAL, side-stand UP, clutch lever pulled in)",
            contextInfo = "Modern motorcycles have 3 interconnected safety cutouts to prevent starting in gear with side stand down.",
            yesTargetId = "res_starter_relay",
            noTargetId = "res_safety_interlock"
        ),
        "res_safety_interlock" to TroubleshootingStep(
            id = "res_safety_interlock",
            question = "",
            conclusionTitle = "Safety Interlock Cutout Active",
            conclusionAdvice = "One of your safety interlocks is cutting the ignition circuit. Actions:\n1. Flip the red handlebar Kill Switch to RUN.\n2. Put motorcycle in Neutral (green 'N' light lit).\n3. Retract the side-stand completely.\n4. Pull the clutch lever all the way to the grip.\n5. If still dead, inspect the side-stand plunger switch for road grime or a stuck pin.",
            isHazardous = false
        ),
        "res_starter_relay" to TroubleshootingStep(
            id = "res_starter_relay",
            question = "",
            conclusionTitle = "Blown Main Fuse or Defective Starter Relay",
            conclusionAdvice = "If switches are verified and battery has 12.6V+ but the starter does not engage at all:\n1. Check the 30A Main Fuse located adjacent to the starter solenoid.\n2. Check clutch perch interlock micro-switch.\nFor serious mechanical, electrical or fuel-system problems, consult a qualified motorcycle technician.",
            isHazardous = true
        ),
        "q_smell_fuel" to TroubleshootingStep(
            id = "q_smell_fuel",
            question = "The engine cranks over strongly, but does not catch fire. Do you hear the fuel pump whine for 2-3 seconds when you first turn the key ON?",
            contextInfo = "Fuel-injected motorcycles prime the fuel rail with a high-pitched buzz when key is switched on.",
            yesTargetId = "q_spark_fuel_stale",
            noTargetId = "res_fuel_pump_fuse"
        ),
        "res_fuel_pump_fuse" to TroubleshootingStep(
            id = "res_fuel_pump_fuse",
            question = "",
            conclusionTitle = "Fuel Pump Inoperative",
            conclusionAdvice = "No fuel pump priming sound indicates the pump is not receiving power. Actions:\n1. Inspect the Fuel Injection / Fuel Pump fuse in the fuse box.\n2. Check Bank Angle Sensor (tip-over sensor) under seat—if knocked out of position, it kills fuel delivery.\nFor serious mechanical, electrical or fuel-system problems, consult a qualified motorcycle technician.",
            isHazardous = true
        ),
        "q_spark_fuel_stale" to TroubleshootingStep(
            id = "q_spark_fuel_stale",
            question = "Has the motorcycle been sitting unridden for more than 3-4 months with old fuel in the tank?",
            contextInfo = "Ethanol fuel degrades within 60-90 days, creating varnish that blocks injector nozzles.",
            yesTargetId = "res_stale_fuel",
            noTargetId = "res_ignition_spark"
        ),
        "res_stale_fuel" to TroubleshootingStep(
            id = "res_stale_fuel",
            question = "",
            conclusionTitle = "Stale / Degraded Fuel Contamination",
            conclusionAdvice = "Old fuel loses light volatile hydrocarbons required for cold ignition and forms gummy deposits in the injector orifices. Actions:\n1. Drain old fuel from tank.\n2. Fill with fresh 91+ octane fuel.\n3. Add dedicated fuel system injector cleaner.\n4. Check spark plugs for fuel fouling.",
            isHazardous = false
        ),
        "res_ignition_spark" to TroubleshootingStep(
            id = "res_ignition_spark",
            question = "",
            conclusionTitle = "Ignition Spark or Compression Issue",
            conclusionAdvice = "If fresh fuel is present and engine cranks:\n1. Test for spark using a spare grounded plug against cylinder head.\n2. Check for loose spark plug caps or blown ignition fuse.\nFor serious mechanical, electrical or fuel-system problems, consult a qualified motorcycle technician.",
            isHazardous = true
        ),

        // Overheating flow
        "q_fan_running" to TroubleshootingStep(
            id = "q_fan_running",
            question = "When the temperature gauge rises high, does the electric radiator cooling fan turn on?",
            contextInfo = "The radiator fan should automatically spin up as coolant climbs above ~100°C.",
            yesTargetId = "q_coolant_level",
            noTargetId = "res_fan_fault"
        ),
        "res_fan_fault" to TroubleshootingStep(
            id = "res_fan_fault",
            question = "",
            conclusionTitle = "Radiator Fan Relay or Thermo-Switch Fault",
            conclusionAdvice = "The cooling fan is not activating. Check the fan fuse, fan motor connection, and radiator thermo-switch. AVOID riding in stop-and-go city traffic until resolved to prevent blown head gasket.\nFor serious mechanical, electrical or fuel-system problems, consult a qualified motorcycle technician.",
            isHazardous = true
        ),
        "q_coolant_level" to TroubleshootingStep(
            id = "q_coolant_level",
            question = "Is the coolant level inside the white overflow expansion reservoir between MIN and MAX?",
            contextInfo = "Check with the motorcycle upright on level ground. NEVER open a hot radiator cap!",
            yesTargetId = "res_thermostat_flow",
            noTargetId = "res_low_coolant"
        ),
        "res_low_coolant" to TroubleshootingStep(
            id = "res_low_coolant",
            question = "",
            conclusionTitle = "Low Coolant / System Leak",
            conclusionAdvice = "Inspect radiator core and rubber hose clamps for green/blue residue. Once engine is completely cold, inspect coolant level at the radiator neck itself. Top up with 50/50 pre-diluted motorcycle coolant.",
            isHazardous = true
        ),
        "res_thermostat_flow" to TroubleshootingStep(
            id = "res_thermostat_flow",
            question = "",
            conclusionTitle = "Stuck Thermostat or Clogged Radiator Fins",
            conclusionAdvice = "If fan spins and coolant is full, the thermostat may be stuck closed preventing flow to radiator, or radiator fins are clogged with mud/road bugs. Clean fins gently with water spray (never high-pressure washers).\nFor serious mechanical, electrical or fuel-system problems, consult a qualified motorcycle technician.",
            isHazardous = true
        ),

        // Spongy Brakes
        "q_lever_to_bar" to TroubleshootingStep(
            id = "q_lever_to_bar",
            question = "Does the brake lever pull easily all the way back until it touches the handlebar grip?",
            contextInfo = "A healthy brake lever should feel firm with minimal travel and a hard wall of resistance.",
            yesTargetId = "res_air_in_brakes",
            noTargetId = "q_brake_pulsing"
        ),
        "res_air_in_brakes" to TroubleshootingStep(
            id = "res_air_in_brakes",
            question = "",
            conclusionTitle = "Air Trapped in Hydraulic Brake Circuit",
            conclusionAdvice = "Air is compressible, whereas hydraulic brake fluid is not. Squeezing compresses the air pocket instead of moving caliper pistons.\nDO NOT RIDE. The brake lines must be flushed and bled with fresh DOT 4 fluid immediately. Inspect master cylinder seal and caliper banjos for fluid seepage.",
            isHazardous = true
        ),
        "q_brake_pulsing" to TroubleshootingStep(
            id = "q_brake_pulsing",
            question = "Does the lever kick back or pulse rhythmically when braking from speed?",
            contextInfo = "Rhythmic lever vibration is linked directly to wheel rotation speed.",
            yesTargetId = "res_warped_rotor",
            noTargetId = "res_worn_pads"
        ),
        "res_warped_rotor" to TroubleshootingStep(
            id = "res_warped_rotor",
            question = "",
            conclusionTitle = "Warped Brake Disc or Stuck Floating Bobbin",
            conclusionAdvice = "The brake disc rotor has excessive lateral runout (warped from heat or locked disc lock) or floating rotor bobbins are frozen with brake dust. Clean rotor bobbins or measure rotor runout with a dial gauge (limit typically 0.15mm).",
            isHazardous = false
        ),
        "res_worn_pads" to TroubleshootingStep(
            id = "res_worn_pads",
            question = "",
            conclusionTitle = "Glazed Brake Pads or Dirty Caliper Pistons",
            conclusionAdvice = "Remove brake pads and inspect for glazing (shiny glass-like friction surface). Clean caliper sliding pins and use brake cleaner to remove pad dust buildup.",
            isHazardous = false
        ),

        // Noise
        "q_noise_type" to TroubleshootingStep(
            id = "q_noise_type",
            question = "Is the noise a fast rhythmic light ticking from the cylinder head, or a heavy dull metallic thud from the lower crankcase?",
            contextInfo = "Listen with the engine idling at operating temperature.",
            yesTargetId = "res_valve_cam_chain",
            noTargetId = "res_bearing_knock"
        ),
        "res_valve_cam_chain" to TroubleshootingStep(
            id = "res_valve_cam_chain",
            question = "",
            conclusionTitle = "Cam Chain Tensioner or Excessive Valve Clearance",
            conclusionAdvice = "Light high-frequency ticking at idle is commonly a worn automatic Cam Chain Tensioner (CCT) allowing the timing chain to slap against its guides, or loose valve clearance. Have a technician inspect timing chain slack to avoid jumped timing.\nFor serious mechanical, electrical or fuel-system problems, consult a qualified motorcycle technician.",
            isHazardous = true
        ),
        "res_bearing_knock" to TroubleshootingStep(
            id = "res_bearing_knock",
            question = "",
            conclusionTitle = "Crankshaft Connecting Rod Bearing Knock (CRITICAL)",
            conclusionAdvice = "A heavy, deep metallic thud that worsens under throttle load indicates spun or severely worn connecting rod or main crankshaft journal bearings.\nSTOP ENGINE IMMEDIATELY. Continuing to run the engine can throw a connecting rod through the crankcase, causing rear wheel lockup and complete destruction.",
            isHazardous = true
        ),

        // Gear shift
        "q_clutch_drag" to TroubleshootingStep(
            id = "q_clutch_drag",
            question = "With the clutch lever pulled all the way in and 1st gear engaged, does the motorcycle try to creep forward on its own?",
            contextInfo = "Test on flat ground with feet on pegs.",
            yesTargetId = "res_clutch_drag",
            noTargetId = "res_shift_linkage"
        ),
        "res_clutch_drag" to TroubleshootingStep(
            id = "res_clutch_drag",
            question = "",
            conclusionTitle = "Clutch Drag / Cable Slack Misadjusted",
            conclusionAdvice = "The clutch plates are not fully separating. Adjust cable free-play at the lever perch or engine pivot to 2-3mm. If hydraulic, bleed the clutch slave cylinder. Also verify proper motorcycle-spec JASO MA2 engine oil is used.",
            isHazardous = false
        ),
        "res_shift_linkage" to TroubleshootingStep(
            id = "res_shift_linkage",
            question = "",
            conclusionTitle = "Loose Foot Shift Linkage or Old Viscosity Oil",
            conclusionAdvice = "Inspect the gear shift pedal linkage rod, ball joints, and splined arm clamp bolt on the shift shaft for looseness. Old engine oil with sheared viscosity significantly degrades smooth shifting feel.",
            isHazardous = false
        ),

        // Vibration
        "q_wobble_speed" to TroubleshootingStep(
            id = "q_wobble_speed",
            question = "Does the vibration or wobble occur at specific road speeds (e.g. 60-80 km/h) or under hard braking?",
            contextInfo = "Note if the handlebar shakes back and forth or if the whole bike buzzes.",
            yesTargetId = "res_wheel_balance",
            noTargetId = "res_steering_head"
        ),
        "res_wheel_balance" to TroubleshootingStep(
            id = "res_wheel_balance",
            question = "",
            conclusionTitle = "Wheel Balance Weight Lost or Tyre Cupping",
            conclusionAdvice = "High-speed handlebar vibration usually indicates an unbalanced front tyre (thrown wheel balance weight) or uneven tyre cupping from incorrect inflation pressure. Check wheel rims for flat spots from potholes.",
            isHazardous = false
        ),
        "res_steering_head" to TroubleshootingStep(
            id = "res_steering_head",
            question = "",
            conclusionTitle = "Loose Steering Head Stem Bearings or Swingarm Pivot",
            conclusionAdvice = "Lift front wheel off the ground; pull forward and push backward on fork lower legs to check for steering stem play. Rotate handlebars slowly to check for a center 'notch' (brinelling). Retorque or replace stem bearings.",
            isHazardous = true
        )
    )

    val warningSigns = listOf(
        WarningSignItem(
            id = "ws_engine_knock",
            title = "Heavy Engine Knocking / Clattering",
            category = "Engine",
            severity = WarningSeverity.CRITICAL,
            whatItIndicates = "Low engine oil pressure, worn rod bearings, or severe pre-ignition detonation.",
            whatRiderShouldCheck = listOf("Engine oil sight glass immediately", "Recent fuel quality", "Oil pressure dashboard warning light"),
            avoidRidingImmediately = true,
            professionalInspectionRecommendation = "Shut off the engine immediately. Do NOT ride. Running an engine with rod knock causes catastrophic engine blowup and rear wheel lockup."
        ),
        WarningSignItem(
            id = "ws_blue_smoke",
            title = "Excessive Blue / Grey Exhaust Smoke",
            category = "Engine",
            severity = WarningSeverity.MEDIUM,
            whatItIndicates = "Engine oil entering combustion chambers past worn piston rings or degraded valve stem oil seals.",
            whatRiderShouldCheck = listOf("Engine oil dipstick level (often drops rapidly)", "Airbox drain hose for pooled oil", "Spark plug fouling"),
            avoidRidingImmediately = false,
            professionalInspectionRecommendation = "Safe for short conservative riding to a service station while monitoring oil level closely. Schedule cylinder compression and valve seal inspection."
        ),
        WarningSignItem(
            id = "ws_white_smoke",
            title = "Sweet-Smelling Thick White Smoke (Liquid Cooled)",
            category = "Engine",
            severity = WarningSeverity.CRITICAL,
            whatItIndicates = "Coolant leaking into cylinders past a blown cylinder head gasket.",
            whatRiderShouldCheck = listOf("Coolant expansion tank level", "Engine oil sight glass for milky chocolate-shake emulsion", "Radiator hose firmness under throttle"),
            avoidRidingImmediately = true,
            professionalInspectionRecommendation = "Stop riding. Coolant rapidly destroys engine bearings and hydraulic lock can bend connecting rods. Workshop head gasket replacement required."
        ),
        WarningSignItem(
            id = "ws_oil_leak",
            title = "Oil Leak on Engine Cases or Near Tyres",
            category = "Engine",
            severity = WarningSeverity.CRITICAL,
            whatItIndicates = "Blown gasket, failed countershaft sprocket seal, or loose oil filter/drain plug.",
            whatRiderShouldCheck = listOf("Path of oil flow: is oil reaching rear tyre or rear brake disc?", "Oil drain bolt and filter tightness", "Engine oil level"),
            avoidRidingImmediately = true,
            professionalInspectionRecommendation = "Never ride if oil can reach the rear tyre or brake rotor, as this causes instant total loss of traction and crashing."
        ),
        WarningSignItem(
            id = "ws_soft_brake",
            title = "Soft / Spongy Brake Lever That Sinks to Grip",
            category = "Brakes",
            severity = WarningSeverity.CRITICAL,
            whatItIndicates = "Air bubbles in brake line, leaking master cylinder seal, or boiled brake fluid.",
            whatRiderShouldCheck = listOf("Brake fluid reservoir levels", "Brake caliper bleed nipples and banjo bolts for dampness", "Brake pad thickness"),
            avoidRidingImmediately = true,
            professionalInspectionRecommendation = "Extremely dangerous. Do NOT ride until system is pressure bled and inspected for hydraulic leaks."
        ),
        WarningSignItem(
            id = "ws_brake_vibe",
            title = "Brake Lever Pulsing / Front End Shudder",
            category = "Brakes",
            severity = WarningSeverity.MEDIUM,
            whatItIndicates = "Warped brake rotor disc, loose caliper mounting bolts, or seized floating disc bobbins.",
            whatRiderShouldCheck = listOf("Rotor thickness and runout", "Floating rotor button rotation", "Caliper mounting torque"),
            avoidRidingImmediately = false,
            professionalInspectionRecommendation = "Ride with increased following distances and avoid aggressive downhill braking. Inspect rotors with a dial gauge."
        ),
        WarningSignItem(
            id = "ws_chain_noise",
            title = "Chain Grinding, Squealing, or Slapping",
            category = "Transmission/Chain",
            severity = WarningSeverity.MEDIUM,
            whatItIndicates = "Dry unlubricated chain, frozen link pins, or excessive chain slack.",
            whatRiderShouldCheck = listOf("Chain slack measurement (target 25-35mm)", "Dry or rusty chain rollers", "Sprocket tooth hooking"),
            avoidRidingImmediately = false,
            professionalInspectionRecommendation = "Clean and lubricate chain immediately. If chain still rattles or has tight and loose spots, replace chain and sprocket set."
        ),
        WarningSignItem(
            id = "ws_excess_chain_slack",
            title = "Excessive Chain Slack (Chain Drooping)",
            category = "Transmission/Chain",
            severity = WarningSeverity.CRITICAL,
            whatItIndicates = "Chain stretched past service wear limits or rear axle adjuster slipped loose.",
            whatRiderShouldCheck = listOf("Rear axle nut torque", "Chain slack at midpoint (if > 45mm)", "Chain tension indicator marks on swingarm"),
            avoidRidingImmediately = true,
            professionalInspectionRecommendation = "A severely slack chain can jump off the rear sprocket and bunch up around the front countershaft, locking the rear wheel or cracking the engine crankcase."
        ),
        WarningSignItem(
            id = "ws_steering_wobble",
            title = "Steering Wobble / Headshake (Tank-Slapper)",
            category = "Wheels",
            severity = WarningSeverity.CRITICAL,
            whatItIndicates = "Loose/damaged steering head stem bearings, front tyre cupping/flat spot, or unbalanced wheel.",
            whatRiderShouldCheck = listOf("Front tyre cold pressure", "Steering head bearing play", "Luggage weight distribution (too much weight over rear)"),
            avoidRidingImmediately = false,
            professionalInspectionRecommendation = "Ride slowly. Avoid letting go of handlebars. Have steering head bearings torqued or replaced before highway speeds."
        ),
        WarningSignItem(
            id = "ws_tyre_damage",
            title = "Tyre Cracking, Bulging, or Deep Cuts",
            category = "Wheels",
            severity = WarningSeverity.CRITICAL,
            whatItIndicates = "Carcass ply delamination, puncture threat, or severe age dry-rot.",
            whatRiderShouldCheck = listOf("Entire tyre circumference for nails, screws, cord exposure, sidewall bubbles"),
            avoidRidingImmediately = true,
            professionalInspectionRecommendation = "A tyre blowout on a motorcycle frequently results in catastrophic crashes. Replace immediately."
        ),
        WarningSignItem(
            id = "ws_engine_light",
            title = "Check Engine Warning Light (FI / MIL) On",
            category = "Electrical",
            severity = WarningSeverity.MEDIUM,
            whatItIndicates = "Engine Control Unit (ECU) has detected an electronic sensor out-of-range (O2 sensor, throttle position sensor, etc.).",
            whatRiderShouldCheck = listOf("Engine running temperature", "Whether engine has entered 'Limp-Home' low-power mode"),
            avoidRidingImmediately = false,
            professionalInspectionRecommendation = "If engine runs smoothly and oil light is OFF, ride to nearest workshop to scan OBD diagnostic trouble codes."
        ),
        WarningSignItem(
            id = "ws_overheating_gauge",
            title = "Engine Temperature Gauge in RED / High Temp Light",
            category = "Engine",
            severity = WarningSeverity.CRITICAL,
            whatItIndicates = "Loss of coolant, failed water pump, stuck thermostat, or inoperative cooling fan.",
            whatRiderShouldCheck = listOf("Pull over safely into shade and turn OFF engine immediately", "Inspect for boiling coolant overflow", "Check if radiator fan is running"),
            avoidRidingImmediately = true,
            professionalInspectionRecommendation = "Allow engine to cool down for at least 30-45 minutes. Do NOT open radiator cap while hot! Continued riding causes cylinder head warping."
        )
    )

    val healthCheckQuestions = listOf(
        HealthQuestion(
            id = "hq_oil_level",
            system = "Engine",
            questionText = "When checking the engine oil sight glass on level ground, where does the oil level sit?",
            options = listOf(
                HealthOption("Between the MIN and MAX lines with clean amber/honey color", true, "Optimal oil level and condition"),
                HealthOption("Near or below the MIN line, or pitch black with burnt smell", false, "Requires immediate top-up or oil change"),
                HealthOption("Milky / frothy light brown color", false, "CRITICAL: Coolant contamination in oil")
            )
        ),
        HealthQuestion(
            id = "hq_brakes_pad",
            system = "Brakes",
            questionText = "How thick is the remaining friction material on your brake pads?",
            options = listOf(
                HealthOption("More than 3mm of pad material with visible wear indicator grooves", true, "Pads in healthy condition"),
                HealthOption("Around 1.5 - 2mm of material (near replacement limit)", false, "Due for replacement soon"),
                HealthOption("Under 1mm or metal backing plate grinding against disc", false, "CRITICAL: Immediate replacement required")
            )
        ),
        HealthQuestion(
            id = "hq_brakes_fluid",
            system = "Brakes",
            questionText = "How does your front brake lever feel when squeezed firmly?",
            options = listOf(
                HealthOption("Crisp, firm resistance with minimal lever travel", true, "Hydraulic circuit healthy"),
                HealthOption("Slightly soft or sponge-like, requiring pumping to build pressure", false, "Air in line; needs bleeding"),
                HealthOption("Lever pulls all the way to the handlebar grip", false, "CRITICAL: Severe leak or boiled fluid")
            )
        ),
        HealthQuestion(
            id = "hq_tyres_pressure",
            system = "Tyres",
            questionText = "When were your cold tyre pressures last checked with an accurate gauge?",
            options = listOf(
                HealthOption("Within the last 7 days and set to manufacturer spec", true, "Good pressure maintenance"),
                HealthOption("Between 2 to 4 weeks ago", false, "Check and calibrate today"),
                HealthOption("More than a month ago or bike feels sluggish in turns", false, "Likely under-inflated, risks handling instability")
            )
        ),
        HealthQuestion(
            id = "hq_tyres_tread",
            system = "Tyres",
            questionText = "What is the condition of your tyre tread and sidewalls?",
            options = listOf(
                HealthOption("Plenty of tread above wear bars, round profile, no cracks", true, "Tyres healthy"),
                HealthOption("Center flattened/squared off from highway mileage", false, "Impairs cornering transition"),
                HealthOption("Tread worn down flush with wear indicator bars, or sidewall dry rot", false, "CRITICAL: Replace tyres immediately")
            )
        ),
        HealthQuestion(
            id = "hq_chain_slack",
            system = "Chain",
            questionText = "How is the tension and lubrication on your drive chain (if chain driven)?",
            options = listOf(
                HealthOption("Clean, lightly lubricated, and between 25-35mm of slack", true, "Proper chain maintenance"),
                HealthOption("Dry rollers or excess slack (over 40mm)", false, "Needs cleaning, lube, and adjustment"),
                HealthOption("Rusty, kinked links that don't straighten, or tooth hooking", false, "CRITICAL: Replace chain & sprockets")
            )
        ),
        HealthQuestion(
            id = "hq_battery_crank",
            system = "Battery",
            questionText = "How does the starter motor sound during cold engine startup?",
            options = listOf(
                HealthOption("Spins fast and starts the engine within 1-2 seconds", true, "Battery voltage strong"),
                HealthOption("Slightly sluggish / hesitates before starting", false, "Charge battery or inspect terminals"),
                HealthOption("Clicks rapidly or dashboard resets during cranking", false, "Battery depleted or failing")
            )
        ),
        HealthQuestion(
            id = "hq_lights_test",
            system = "Lights",
            questionText = "Do all headlights, high beams, turn signals, and brake lights function properly?",
            options = listOf(
                HealthOption("Yes, all lights and horn work 100% properly", true, "Full electrical visibility verified"),
                HealthOption("One turn signal or license plate bulb is out", false, "Replace burned bulb for safety"),
                HealthOption("Brake light or headlight not working", false, "CRITICAL: Major safety violation and hazard")
            )
        )
    )

    val preRideChecklistItems = listOf(
        "Tyres: Inspect cold pressure, tread depth, and check for embedded screws/glass",
        "Brakes: Squeeze front lever and press rear pedal for firm resistance and pad thickness",
        "Lights & Horn: Test high/low beam, front & rear turn signals, brake lights, and horn",
        "Mirrors: Clean glass and adjust angles for full rearward lane visibility",
        "Fuel: Check fuel gauge or tank level before embarking",
        "Chain: Verify light lubrication, clean links, and 25-35mm slack",
        "Controls: Smooth throttle snap-back, clutch free play, and handlebar rotation lock-to-lock",
        "Fluids: Inspect sight glass for engine oil level and look under bike for leaks"
    )

    val longRideChecklistItems = listOf(
        "Tyres: Calibrate cold pressures for luggage/passenger weight; inspect valve stems",
        "Brakes: Check pad thickness (min 2.5mm for long trips) and fluid reservoir clarity",
        "Engine Oil: Fresh oil and verified level between MIN and MAX marks",
        "Coolant: Check reservoir level on level ground and check radiator fins for debris",
        "Chain: Deep clean, lubricate, and adjust slack; pack travel chain lube spray",
        "Battery: Clean terminals and test resting voltage (12.6V+)",
        "Lights: Verify headlight beam alignment with luggage weight compression on rear shock",
        "Tools: Portable puncture repair kit (plugs + CO2 inflator), metric hex/spanner multi-tool",
        "Emergency Equipment: First aid kit, high-visibility vest, mini flashlight / headlamp",
        "Documents: Vehicle registration, insurance card, driver's license, emergency contacts",
        "Riding Gear: Weather-appropriate jacket/pants with armor, rain layer, certified helmet, gloves, boots"
    )

    val careGuides = listOf(
        CareGuideItem(
            id = "cg_wash",
            title = "Safe Motorcycle Washing & Detailing",
            category = "Cleaning",
            summary = "How to clean your motorcycle thoroughly without damaging sensitive electronics, wheel bearings, or radiator fins.",
            steps = listOf(
                "Ensure engine and exhaust pipes are completely cold before spraying water (cold water on hot cylinder heads can cause metal warping or cracking).",
                "Cover exhaust pipe outlet with an exhaust plug or plastic bag held by a rubber band to prevent water entering the muffler.",
                "Rinse with gentle garden hose pressure from top to bottom. NEVER point high-pressure jet washers at wheel bearings, swingarm pivot, steering head, chain O-rings, or radiator fins.",
                "Use a pH-neutral dedicated motorcycle shampoo and microfiber wash mitt. Wash from cleanest upper areas (tank, fairing) down to dirty lower areas (wheels, swingarm).",
                "Rinse thoroughly with clean water to prevent soap spot etching.",
                "Dry completely using a motorcycle leaf blower/dryer or plush microfiber drying towels. Never leave water pooling around spark plug wells or switchgear.",
                "Start engine and idle for 2-3 minutes to evaporate residual water from engine nooks and exhaust header, then apply chain lube immediately."
            ),
            warnings = listOf(
                "Do NOT use household dishwashing soap (strips wax and contains salts that accelerate aluminum corrosion).",
                "Do NOT spray tire treads with tire-shine silicone (extremely slick, will cause immediate crash in first corner!)."
            ),
            recommendedTools = listOf("pH-neutral wash shampoo", "Microfiber wash mitt", "Soft wheel brush", "Plush drying towels", "Exhaust wash plug")
        ),
        CareGuideItem(
            id = "cg_chain",
            title = "Chain Deep Cleaning & Lubrication",
            category = "Maintenance",
            summary = "The definitive guide to extending your drive chain and sprocket life from 15,000 km to over 35,000 km.",
            steps = listOf(
                "Place motorcycle on rear paddock stand or center stand with engine OFF and transmission in NEUTRAL.",
                "Spray dedicated O-ring safe chain cleaner or pure deodorized kerosene over the entire chain run.",
                "Use a 3-sided grunge brush to scrub all four sides of the chain links, dislodging road grit and old waxy buildup.",
                "Wipe chain thoroughly dry with clean disposable shop rags until rollers shine metallic silver.",
                "Take the motorcycle for a short 2-3 minute ride to warm the chain metal slightly.",
                "Apply quality O-ring chain lube exclusively to the INNER run of the bottom chain span, aiming at the rubber O-rings between link plates.",
                "Allow lubricant solvents to evaporate for 20-30 minutes before riding to prevent fling onto rear wheel and brake disc."
            ),
            warnings = listOf(
                "NEVER run the engine in gear while cleaning a chain with fingers or a rag! Fingers can be instantly trapped and amputated between chain and rear sprocket.",
                "Do NOT use gasoline, wire brushes, or brake cleaner on O-ring chains (destroys rubber seals)."
            ),
            recommendedTools = listOf("3-sided chain grunge brush", "Kerosene or O-ring chain cleaner", "Motorcycle synthetic chain lube", "Paddock stand")
        ),
        CareGuideItem(
            id = "cg_rust",
            title = "Rust Prevention & Metal Protection",
            category = "Protection",
            summary = "Defend against winter road salt, humid coastal air, and aluminum oxidation.",
            steps = listOf(
                "Apply anti-corrosion barrier sprays (such as ACF-50 or XCP Corrosion Blocker) twice a year.",
                "Spray onto clean rag and wipe across engine cases, frame welds, fasteners, brake lines, and under-seat brackets.",
                "Use silicone grease on rubber seals and fork wiper boots to prevent drying and ozone cracking.",
                "Touch up any exposed rock-chip paint on frame tubes promptly with rust-inhibiting primer."
            ),
            warnings = listOf(
                "NEVER spray anti-corrosion barrier sprays or silicone onto brake discs, calipers, or tyre treads!"
            ),
            recommendedTools = listOf("ACF-50 / XCP corrosion inhibitor", "Lint-free cloths", "Silicone dielectric grease")
        ),
        CareGuideItem(
            id = "cg_storage",
            title = "Seasonal Winterizing & Storage Guide",
            category = "Storage",
            summary = "Prevent battery death, fuel varnish, and tyre flat-spotting during inactive storage.",
            steps = listOf(
                "Change engine oil and filter immediately prior to storage to remove acidic combustion residues.",
                "Top off fuel tank to 100% full to eliminate air space and add quality fuel stabilizer; run engine for 5 minutes.",
                "Remove battery and connect to a smart multi-stage battery tender, or plug into an on-bike quick-connect harness.",
                "Raise motorcycle off ground on paddock stands to avoid flat-spotting tyre carcasses.",
                "Plug exhaust pipe and airbox inlet with breathable mesh or steel wool to prevent mice nesting.",
                "Cover with a breathable indoor motorcycle cover (avoid moisture-trapping non-breathable plastic tarps)."
            ),
            warnings = listOf(
                "Do NOT start the engine periodically for 5 minutes in winter—it creates severe water condensation in the engine oil!"
            ),
            recommendedTools = listOf("Smart battery tender", "Fuel stabilizer", "Front and rear paddock stands", "Breathable cover")
        )
    )

    val modifications = listOf(
        ModificationItem(
            id = "mod_exhaust",
            title = "Aftermarket Exhaust Systems (Slip-On vs Full System)",
            category = "Exhaust",
            purpose = "Reduce motorcycle weight, alter exhaust sound note, and optimize gas flow.",
            potentialBenefits = listOf("Weight reduction (often 3 to 7 kg lighter than stock)", "Richer sound tone", "Small horsepower gains when paired with ECU tuning"),
            possibleDrawbacks = listOf("Slip-ons offer minimal power gains without tune", "Full decat systems run lean without fuel remap, risking burned exhaust valves", "Noise complaints and strict road legality limits"),
            compatibilityNotes = "Requires matching header diameter. Full systems often eliminate the catalytic converter and require an O2 sensor bung or lambda eliminator.",
            safetyConsiderations = "Excessively loud exhausts cause hearing fatigue on long rides. Poorly routed pipes can melt plastic fairing or burn luggage.",
            legalRegulatoryNotes = "Check local EPA/EU homologation regulations. Street legal exhausts require dB-killers and catalytic converter retention."
        ),
        ModificationItem(
            id = "mod_suspension",
            title = "Suspension Spring Rate & Cartridge Kits",
            category = "Suspension",
            purpose = "Match motorcycle damping and spring stiffness to rider weight, riding style, and luggage load.",
            potentialBenefits = listOf("Dramatic improvement in cornering stability and tyre grip", "Reduced front-end dive under heavy emergency braking", "Greatly improved ride comfort over rough asphalt"),
            possibleDrawbacks = listOf("Expensive upgrade cost", "Requires proper sag setup and damping tuning by knowledgeable technician"),
            compatibilityNotes = "Must specify rider weight including full gear (usually rider weight + 8 kg for helmet, boots, jacket).",
            safetyConsiderations = "One of the best safety upgrades available—proper suspension keeps your tyres in continuous contact with the road.",
            legalRegulatoryNotes = "Fully road legal."
        ),
        ModificationItem(
            id = "mod_brakes_lines",
            title = "Steel-Braided Brake Lines & High-Performance Pads",
            category = "Brakes",
            purpose = "Eliminate hydraulic line expansion under hard braking and increase braking bite.",
            potentialBenefits = listOf("Zero volumetric line expansion = rock-firm lever feel", "Consistent lever pressure in hot conditions and mountain descents", "Sintered pads offer immediate, high-friction stopping bite"),
            possibleDrawbacks = listOf("Aggressive track pads require warm-up and accelerate rotor wear", "Improper bleeding can trap air bubbles"),
            compatibilityNotes = "Kit must match ABS routing lengths and banjo bolt thread pitch (M10x1.0 vs M10x1.25).",
            safetyConsiderations = "Ensure brake lines do not bind or stretch when front suspension is at full extension or when handlebars are at full steering lock.",
            legalRegulatoryNotes = "Ensure lines carry DOT / TUV safety certification."
        ),
        ModificationItem(
            id = "mod_crash_protection",
            title = "Crash Bungs, Engine Sliders, & Barkbusters",
            category = "Protection",
            purpose = "Protect expensive engine casings, radiators, frame, and handlebar levers during low-speed drops.",
            potentialBenefits = listOf("Saves thousands in repair bills from accidental parking lot drops", "Prevents broken clutch/brake levers so you can ride home"),
            possibleDrawbacks = listOf("Cheap single-point frame sliders can act as levers to crack engine mounting tabs in high-speed crashes"),
            compatibilityNotes = "Choose sliders that mount to multi-point subframe brackets or crash bars rather than bare cylinder head bolts.",
            safetyConsiderations = "High safety benefit. Saves vital fluid retention after a tip-over.",
            legalRegulatoryNotes = "Road legal worldwide."
        ),
        ModificationItem(
            id = "mod_lighting",
            title = "Auxiliary LED Fog Lights & Tail Flashers",
            category = "Lighting",
            purpose = "Dramatically increase forward visibility in fog/rain and enhance visual conspicuity to oncoming cars.",
            potentialBenefits = listOf("Wider road illumination at night", "Triangular light pattern makes distance and speed easier for car drivers to judge", "Pulsing brake lights alert tailgating drivers"),
            possibleDrawbacks = listOf("Can dazzle oncoming drivers if aimed too high", "Additional electrical wattage draw on stator"),
            compatibilityNotes = "Use a dedicated relay and fuse harness wired to an ignition-switched trigger wire.",
            safetyConsiderations = "Aim beams low and to the road shoulders to prevent blinding other road users.",
            legalRegulatoryNotes = "Check local regulations regarding auxiliary light beam colors (amber/white allowed) and flash frequencies."
        )
    )
}
