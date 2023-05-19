package com.ayd.pushapp.data.database

import androidx.room.*
import com.ayd.pushapp.model.DayData

@Dao
interface DayDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dayData: DayData)

    @Query("SELECT * FROM day_data WHERE id = :dayId")
    suspend fun getDayDataById(dayId: Int): DayData

    @Query("DELETE FROM day_data")
    suspend fun deleteAllDayData()

    @Query("DELETE FROM day_Data WHERE id = :dayId")
    suspend fun deleteDayDataById(dayId: Int)
}