package com.ayd.pushapp.feature.pagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentFourthWeekBinding
import com.ayd.pushapp.databinding.FragmentSixthWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData


class SixthWeek : Fragment() {

    private var _binding: FragmentSixthWeekBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSixthWeekBinding.inflate(inflater, container, false)


        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 6,
                    days = listOf(
                        DayData("Monday", 60, listOf()),
                        DayData("Tuesday", 45, listOf()),
                        DayData("Wednesday", 90, listOf()),
                        DayData("Thursday", 30, listOf()),
                        DayData("Friday", 120, listOf())
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 6,
                    days = listOf(
                        DayData("Monday", 60, listOf()),
                        DayData("Tuesday", 45, listOf()),
                        DayData("Wednesday", 90, listOf()),
                        DayData("Thursday", 30, listOf()),
                        DayData("Friday", 120, listOf())
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 6,
                    days = listOf(
                        DayData("Monday", 60, listOf(91,80,78,78,72,71,93)),
                        DayData("Tuesday", 45, listOf(55,52,50,50,48,46,44,95)),
                        DayData("Wednesday", 90, listOf(95,90,85,82,80,70,97)),
                        DayData("Thursday", 30, listOf(60,50,48,48,46,44,42,98)),
                        DayData("Friday", 120, listOf(100,95,92,90,100))
                    )
                )
            }
            else -> {
                // Handle the case when level is not "level1", "level2", or "level3"
                weekData = null
            }
        }

        // Set click listeners for the days
        if (weekData != null) {
            binding.day1.setOnClickListener {
                navigateToSportFragment(weekData, 0)
            }
            binding.day2.setOnClickListener {
                navigateToSportFragment(weekData, 1)
            }
            binding.day3.setOnClickListener {
                navigateToSportFragment(weekData, 2)
            }
            binding.day4.setOnClickListener {
                navigateToSportFragment(weekData, 3)
            }
            binding.day5.setOnClickListener {
                navigateToSportFragment(weekData, 4)
            }
        }

        return binding.root
    }

    private fun navigateToSportFragment(weekData: WeekData, index: Int) {
        val action =
            ViewPagerFragmentDirections.actionViewPagerFragmentToSportFragment(weekData, index)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}