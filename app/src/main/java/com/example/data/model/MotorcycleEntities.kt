package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_motorcycle")
data class UserMotorcycleEntity(
    @PrimaryKey val id: Int = 1,
    val brand: String,
    val model: String,
    val year: Int,
    val nickname: String = "",
    val currentKm: Int,
    val purchaseDate: String,
    val lastServiceDate: String,
    val lastServiceKm: Int,
    val oilLastChangedKm: Int = lastServiceKm,
    val chainLastMaintainedKm: Int = lastServiceKm
)

@Entity(tableName = "service_history")
data class ServiceHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val mileageKm: Int,
    val serviceType: String, // Regular, Periodic, Oil Change, Chain, Repair, Inspection, Tire
    val partsReplaced: String = "",
    val oilReplaced: String = "",
    val cost: Double = 0.0,
    val workshop: String = "DIY",
    val notes: String = "",
    val receiptRef: String = ""
)

@Entity(tableName = "checklist_records")
data class ChecklistRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val checklistType: String, // "Pre-Ride" or "Long-Ride"
    val timestamp: Long = System.currentTimeMillis(),
    val itemsCompletedCount: Int,
    val totalItemsCount: Int,
    val notes: String = ""
)

@Entity(tableName = "custom_guides")
data class CustomGuideEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val category: String, // Maintenance, Parts, Troubleshooting, Safety, Modification
    val content: String,
    val author: String = "Admin",
    val isPublished: Boolean = true,
    val lastUpdated: Long = System.currentTimeMillis()
)
