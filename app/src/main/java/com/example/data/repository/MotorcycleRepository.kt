package com.example.data.repository

import com.example.data.db.AppDatabase
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MotorcycleRepository(private val db: AppDatabase) {

    val userMotorcycle: Flow<UserMotorcycleEntity?> = db.motorcycleDao().getUserMotorcycle()
    val allServiceRecords: Flow<List<ServiceHistoryEntity>> = db.serviceHistoryDao().getAllRecords()
    val allChecklistRecords: Flow<List<ChecklistRecordEntity>> = db.checklistDao().getAllChecklistRecords()
    val customGuides: Flow<List<CustomGuideEntity>> = db.customGuideDao().getAllGuides()

    suspend fun initDefaultMotorcycleIfNeeded() {
        // Will be called on startup to seed initial user bike if empty
    }

    suspend fun saveUserMotorcycle(motorcycle: UserMotorcycleEntity) {
        db.motorcycleDao().insertOrUpdate(motorcycle)
    }

    suspend fun updateMileage(newKm: Int) {
        db.motorcycleDao().updateMileage(newKm)
    }

    suspend fun addServiceRecord(record: ServiceHistoryEntity): Long {
        return db.serviceHistoryDao().insertRecord(record)
    }

    suspend fun updateServiceRecord(record: ServiceHistoryEntity) {
        db.serviceHistoryDao().updateRecord(record)
    }

    suspend fun deleteServiceRecord(record: ServiceHistoryEntity) {
        db.serviceHistoryDao().deleteRecord(record)
    }

    suspend fun deleteServiceRecordById(id: Long) {
        db.serviceHistoryDao().deleteById(id)
    }

    suspend fun addChecklistRecord(record: ChecklistRecordEntity): Long {
        return db.checklistDao().insertChecklistRecord(record)
    }

    suspend fun addCustomGuide(guide: CustomGuideEntity): Long {
        return db.customGuideDao().insertGuide(guide)
    }

    suspend fun deleteCustomGuide(id: Long) {
        db.customGuideDao().deleteGuideById(id)
    }

    fun calculateReminders(
        currentKm: Int,
        lastServiceKm: Int,
        oilChangedKm: Int,
        chainLubeKm: Int,
        spec: MotorcycleSpec
    ): List<ServiceReminderItem> {
        val reminders = mutableListOf<ServiceReminderItem>()

        // 1. Regular Service (e.g. 6000 km)
        val nextRegularDueKm = lastServiceKm + spec.serviceIntervalKm
        val kmToRegular = nextRegularDueKm - currentKm
        val regStatus = when {
            kmToRegular <= 0 -> ReminderStatus.OVERDUE
            kmToRegular <= 500 -> ReminderStatus.DUE_SOON
            else -> ReminderStatus.GOOD
        }
        reminders.add(
            ServiceReminderItem(
                id = "rem_regular_service",
                name = "Scheduled Regular Service",
                intervalKm = spec.serviceIntervalKm,
                intervalMonths = 6,
                currentKm = currentKm,
                lastDoneKm = lastServiceKm,
                status = regStatus,
                dueKmRemaining = kmToRegular,
                importance = "Follow official service schedule"
            )
        )

        // 2. Engine Oil & Filter Change
        val oilInterval = spec.serviceIntervalKm
        val nextOilDueKm = oilChangedKm + oilInterval
        val kmToOil = nextOilDueKm - currentKm
        val oilStatus = when {
            kmToOil <= 0 -> ReminderStatus.OVERDUE
            kmToOil <= 400 -> ReminderStatus.DUE_SOON
            else -> ReminderStatus.GOOD
        }
        reminders.add(
            ServiceReminderItem(
                id = "rem_oil_change",
                name = "Engine Oil & Filter Change",
                intervalKm = oilInterval,
                intervalMonths = 12,
                currentKm = currentKm,
                lastDoneKm = oilChangedKm,
                status = oilStatus,
                dueKmRemaining = kmToOil,
                importance = "Critical for engine longevity"
            )
        )

        // 3. Chain Cleaning & Lube (if chain driven)
        if (spec.chainServiceIntervalKm > 0) {
            val chainInterval = spec.chainServiceIntervalKm
            val nextChainKm = chainLubeKm + chainInterval
            val kmToChain = nextChainKm - currentKm
            val chainStatus = when {
                kmToChain <= 0 -> ReminderStatus.OVERDUE
                kmToChain <= 100 -> ReminderStatus.DUE_SOON
                else -> ReminderStatus.GOOD
            }
            reminders.add(
                ServiceReminderItem(
                    id = "rem_chain_lube",
                    name = "Chain Clean, Lube & Tension",
                    intervalKm = chainInterval,
                    intervalMonths = 1,
                    currentKm = currentKm,
                    lastDoneKm = chainLubeKm,
                    status = chainStatus,
                    dueKmRemaining = kmToChain,
                    importance = "Extend drivetrain life"
                )
            )
        }

        // 4. Brake Inspection
        val brakeInterval = 3000
        val kmToBrake = (lastServiceKm + brakeInterval) - currentKm
        val brakeStatus = when {
            kmToBrake <= 0 -> ReminderStatus.OVERDUE
            kmToBrake <= 300 -> ReminderStatus.DUE_SOON
            else -> ReminderStatus.GOOD
        }
        reminders.add(
            ServiceReminderItem(
                id = "rem_brake_inspection",
                name = "Brake Pads & Fluid Inspection",
                intervalKm = brakeInterval,
                intervalMonths = 6,
                currentKm = currentKm,
                lastDoneKm = lastServiceKm,
                status = brakeStatus,
                dueKmRemaining = kmToBrake,
                importance = "Vital safety system"
            )
        )

        // 5. Cold Tyre Inspection & Pressure Check
        reminders.add(
            ServiceReminderItem(
                id = "rem_tyres",
                name = "Tyre Pressure & Tread Check",
                intervalKm = 500,
                intervalMonths = 1,
                currentKm = currentKm,
                lastDoneKm = currentKm - 150, // weekly check
                status = ReminderStatus.GOOD,
                dueKmRemaining = 350,
                importance = "Weekly safety priority"
            )
        )

        // 6. Battery State of Charge & Terminals
        reminders.add(
            ServiceReminderItem(
                id = "rem_battery",
                name = "Battery Voltage & Terminals",
                intervalKm = 5000,
                intervalMonths = 3,
                currentKm = currentKm,
                lastDoneKm = lastServiceKm,
                status = ReminderStatus.GOOD,
                dueKmRemaining = 2400,
                importance = "Prevent stranding"
            )
        )

        return reminders
    }
}
