package com.ayd.pushapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ayd.pushapp.data.database.DayDataConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "day_data")
data class DayData(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val dayOfWeek: String,
    val timeValue: Int,
    val numbers: List<Int>
): Parcelable
