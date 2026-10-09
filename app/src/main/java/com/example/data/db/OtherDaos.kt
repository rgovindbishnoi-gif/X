package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.ChecklistRecordEntity
import com.example.data.model.CustomGuideEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChecklistDao {
    @Query("SELECT * FROM checklist_records ORDER BY timestamp DESC")
    fun getAllChecklistRecords(): Flow<List<ChecklistRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChecklistRecord(record: ChecklistRecordEntity): Long
}

@Dao
interface CustomGuideDao {
    @Query("SELECT * FROM custom_guides ORDER BY lastUpdated DESC")
    fun getAllGuides(): Flow<List<CustomGuideEntity>>

    @Query("SELECT * FROM custom_guides WHERE isPublished = 1 ORDER BY lastUpdated DESC")
    fun getPublishedGuides(): Flow<List<CustomGuideEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGuide(guide: CustomGuideEntity): Long

    @Query("DELETE FROM custom_guides WHERE id = :id")
    suspend fun deleteGuideById(id: Long)
}
