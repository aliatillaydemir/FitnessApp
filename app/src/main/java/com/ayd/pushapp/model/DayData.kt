package com.ayd.pushapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayData(
    val dayOfWeek: String,
    val timeValue: Int,
    val numbers: List<Int>
): Parcelable
