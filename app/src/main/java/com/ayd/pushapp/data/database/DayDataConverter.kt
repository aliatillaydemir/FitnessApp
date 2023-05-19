package com.ayd.pushapp.data.database

import androidx.room.TypeConverter
import com.ayd.pushapp.model.DayData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DayDataConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromList(numbers: List<Int>): String {
        return gson.toJson(numbers)
    }

    @TypeConverter
    fun toList(numbersString: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(numbersString, listType)
    }

}