package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.ServiceHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceHistoryDao {
    @Query("SELECT * FROM service_history ORDER BY mileageKm DESC, id DESC")
    fun getAllRecords(): Flow<List<ServiceHistoryEntity>>

    @Query("SELECT * FROM service_history WHERE serviceType LIKE '%' || :query || '%' OR workshop LIKE '%' || :query || '%' OR notes LIKE '%' || :query || '%' ORDER BY mileageKm DESC")
    fun searchRecords(query: String): Flow<List<ServiceHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: ServiceHistoryEntity): Long

    @Update
    suspend fun updateRecord(record: ServiceHistoryEntity)

    @Delete
    suspend fun deleteRecord(record: ServiceHistoryEntity)

    @Query("DELETE FROM service_history WHERE id = :id")
    suspend fun deleteById(id: Long)
}
