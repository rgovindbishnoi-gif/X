package com.example.data.model

data class MotorcycleSpec(
    val id: String,
    val brand: String,
    val model: String,
    val modelYear: Int,
    val category: String, // Naked, Sport, Adventure, Cruiser, Classic
    val engineType: String,
    val engineDisplacement: String,
    val transmission: String,
    val fuelSystem: String,
    val coolingSystem: String,
    val frontBrake: String,
    val rearBrake: String,
    val frontSuspension: String,
    val rearSuspension: String,
    val tyreSizes: String,
    val fuelCapacity: String,
    val weight: String,
    val dimensions: String,
    val oilCapacity: String,
    val serviceIntervalKm: Int = 6000,
    val chainServiceIntervalKm: Int = 800,
    val isDemoData: Boolean = true
)

data class StageInfo(
    val stageNumber: Int,
    val title: String,
    val subtitle: String,
    val iconName: String,
    val keyItems: List<String>,
    val fullExplanation: String,
    val diyFriendly: Boolean = true,
    val manufacturerNotice: String = "Always follow the motorcycle manufacturer's official service schedule."
)

enum class LearningLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
}

enum class ComponentSystem(val title: String) {
    ENGINE("Engine System"),
    TRANSMISSION("Transmission & Clutch"),
    FUEL_AIR("Fuel & Intake"),
    EXHAUST("Exhaust System"),
    BRAKES("Braking System"),
    SUSPENSION("Suspension & Chassis"),
    ELECTRICAL("Electrical System"),
    DRIVETRAIN("Drivetrain"),
    WHEELS("Tyres & Wheels")
}

data class MotorcyclePart(
    val id: String,
    val name: String,
    val system: ComponentSystem,
    val location: String,
    val function: String,
    val beginnerExplanation: String,
    val intermediateExplanation: String,
    val advancedExplanation: String,
    val maintenance: String,
    val commonProblems: List<String>,
    val warningSigns: List<String>,
    val replacementConsiderations: String
)

data class TroubleshootingStep(
    val id: String,
    val question: String,
    val contextInfo: String = "",
    val yesTargetId: String? = null,
    val noTargetId: String? = null,
    val conclusionTitle: String? = null,
    val conclusionAdvice: String? = null,
    val isHazardous: Boolean = false
)

data class TroubleshootingCategory(
    val id: String,
    val title: String,
    val icon: String,
    val rootStepId: String,
    val description: String
)

data class WarningSignItem(
    val id: String,
    val title: String,
    val category: String, // Engine, Brakes, Transmission/Chain, Wheels, Electrical
    val severity: WarningSeverity,
    val whatItIndicates: String,
    val whatRiderShouldCheck: List<String>,
    val avoidRidingImmediately: Boolean,
    val professionalInspectionRecommendation: String
)

enum class WarningSeverity {
    LOW,
    MEDIUM,
    CRITICAL
}

data class HealthQuestion(
    val id: String,
    val system: String, // Engine, Brakes, Tyres, Chain, Battery, Lights, Suspension, Fluids
    val questionText: String,
    val options: List<HealthOption>
)

data class HealthOption(
    val text: String,
    val isGood: Boolean,
    val note: String = ""
)

data class CareGuideItem(
    val id: String,
    val title: String,
    val category: String,
    val summary: String,
    val steps: List<String>,
    val warnings: List<String>,
    val recommendedTools: List<String>
)

data class ModificationItem(
    val id: String,
    val title: String,
    val category: String, // Exhaust, Air Intake, Suspension, Brakes, Lighting, Cosmetic, Tyres, Ergonomics
    val purpose: String,
    val potentialBenefits: List<String>,
    val possibleDrawbacks: List<String>,
    val compatibilityNotes: String,
    val safetyConsiderations: String,
    val legalRegulatoryNotes: String
)

data class ServiceReminderItem(
    val id: String,
    val name: String,
    val intervalKm: Int,
    val intervalMonths: Int,
    val currentKm: Int,
    val lastDoneKm: Int,
    val status: ReminderStatus,
    val dueKmRemaining: Int,
    val importance: String
)

enum class ReminderStatus {
    GOOD,
    DUE_SOON,
    OVERDUE
}
