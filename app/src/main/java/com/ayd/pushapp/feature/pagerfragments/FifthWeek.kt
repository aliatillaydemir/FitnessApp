package com.ayd.pushapp.feature.pagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentFifthWeekBinding
import com.ayd.pushapp.databinding.FragmentFirstWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData


class FifthWeek : Fragment() {

    private var _binding: FragmentFifthWeekBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFifthWeekBinding.inflate(inflater, container, false)


        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 5,
                    days = listOf(
                        DayData("Monday", 60, listOf(20,18,16,14,20)),
                        DayData("Tuesday", 45, listOf(15,19,14,13,22)),
                        DayData("Wednesday", 90, listOf(17,22,16,15,22)),
                        DayData("Thursday", 30, listOf(21,12,10,8,6,5,4,23)),
                        DayData("Friday", 120, listOf(25,23,22,20,25))
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 5,
                    days = listOf(
                        DayData("Monday", 60, listOf(46,43,29,41,46)),
                        DayData("Tuesday", 45, listOf(25,25,26,26,24,24,22,47)),
                        DayData("Wednesday", 90, listOf(50,48,45,45,48)),
                        DayData("Thursday", 30, listOf(23,23,25,25,22,22,20,48)),
                        DayData("Friday", 120, listOf(50,48,46,44,50))
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 5,
                    days = listOf(
                        DayData("Monday", 60, listOf(81,60,64,66,68,70,83)),
                        DayData("Tuesday", 45, listOf(45,42,40,40,38,36,36,85)),
                        DayData("Wednesday", 90, listOf(85,80,75,72,70,65,87)),
                        DayData("Thursday", 30, listOf(45,40,38,38,36,34,32,88)),
                        DayData("Friday", 120, listOf(90,85,82,80,90))
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