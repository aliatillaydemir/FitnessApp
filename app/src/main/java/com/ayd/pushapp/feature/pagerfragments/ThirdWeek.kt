package com.ayd.pushapp.feature.pagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentFirstWeekBinding
import com.ayd.pushapp.databinding.FragmentThirdWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData


class ThirdWeek : Fragment() {

    private var _binding: FragmentThirdWeekBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentThirdWeekBinding.inflate(inflater, container, false)

        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 3,
                    days = listOf(
                        DayData("Monday", 60, listOf(11,12,7,5,10)),
                        DayData("Tuesday", 45, listOf(12,8,7,6,11)),
                        DayData("Wednesday", 90, listOf(13,10,8,6,12)),
                        DayData("Thursday", 30, listOf(12,8,6,5,4,3,2,13)),
                        DayData("Friday", 120, listOf(15,14,12,10,15))
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 3,
                    days = listOf(
                        DayData("Monday", 60, listOf(36,33,32,30,36)),
                        DayData("Tuesday", 45, listOf(15,15,18,18,15,12,10,37)),
                        DayData("Wednesday", 90, listOf(38,38,35,35,38)),
                        DayData("Thursday", 30, listOf(13,13,18,15,12,12,10,38)),
                        DayData("Friday", 120, listOf(40,38,36,34,40))
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 3,
                    days = listOf(
                        DayData("Monday", 60, listOf(61,50,48,45,44,40,63)),
                        DayData("Tuesday", 45, listOf(36,35,35,30,34,34,32,65)),
                        DayData("Wednesday", 90, listOf(65,60,55,52,50,44,66)),
                        DayData("Thursday", 30, listOf(33,32,28,35,32,30,28,68)),
                        DayData("Friday", 120, listOf(70,65,63,60,70))
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