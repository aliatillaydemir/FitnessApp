package com.ayd.pushapp.feature.mainfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayd.pushapp.R
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData


class SportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val weekData = WeekData(
            weekNumber = 1,
            days = listOf(
                DayData("Monday", 60, listOf(2, 3, 2, 2, 3)),
                DayData("Tuesday", 45, listOf(1, 3, 2, 1, 4)),
                // ... remaining days
            )
        )



        return inflater.inflate(R.layout.fragment_sport, container, false)
    }


}