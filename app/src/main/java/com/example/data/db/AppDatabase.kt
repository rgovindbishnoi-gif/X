package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.ChecklistRecordEntity
import com.example.data.model.CustomGuideEntity
import com.example.data.model.ServiceHistoryEntity
import com.example.data.model.UserMotorcycleEntity

@Database(
    entities = [
        UserMotorcycleEntity::class,
        ServiceHistoryEntity::class,
        ChecklistRecordEntity::class,
        CustomGuideEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun motorcycleDao(): MotorcycleDao
    abstract fun serviceHistoryDao(): ServiceHistoryDao
    abstract fun checklistDao(): ChecklistDao
    abstract fun customGuideDao(): CustomGuideDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "motorcycle_guide_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
