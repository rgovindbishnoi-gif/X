package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.UserMotorcycleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MotorcycleDao {
    @Query("SELECT * FROM user_motorcycle WHERE id = 1 LIMIT 1")
    fun getUserMotorcycle(): Flow<UserMotorcycleEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(motorcycle: UserMotorcycleEntity)

    @Update
    suspend fun update(motorcycle: UserMotorcycleEntity)

    @Query("UPDATE user_motorcycle SET currentKm = :newKm WHERE id = 1")
    suspend fun updateMileage(newKm: Int)
}
