package com.ayd.pushapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class WeekData(
    val weekNumber: Int,
    val days: @RawValue List<DayData>
): Parcelable
