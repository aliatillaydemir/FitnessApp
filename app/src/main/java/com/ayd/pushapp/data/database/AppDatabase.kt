package com.ayd.pushapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayd.pushapp.model.DayData

@Database(entities = [DayData::class], version = 1)
@TypeConverters(DayDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dayDataDao(): DayDataDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                .build()
        }
    }
}
