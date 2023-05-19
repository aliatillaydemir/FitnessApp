package com.ayd.pushapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayd.pushapp.model.DayData

@Dao
interface DayDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dayData: DayData)

    @Query("SELECT * FROM day_data WHERE id = :dayId")
    suspend fun getDayDataById(dayId: Int): DayData

}