package com.ayd.pushapp.feature.pagerfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentFirstWeekBinding
import com.ayd.pushapp.databinding.FragmentSecondWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData


class FirstWeek : Fragment() {

    private var _binding: FragmentFirstWeekBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFirstWeekBinding.inflate(inflater, container, false)

        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 1,
                    days = listOf(
                        DayData("Monday", 60, listOf(2,3,2,2,3)),
                        DayData("Tuesday", 45, listOf(3,4,2,3,4)),
                        DayData("Wednesday", 90, listOf(4,5,4,4,5)),
                        DayData("Thursday", 30, listOf(4,2,1,3,1,2,1,5)),
                        DayData("Friday", 120, listOf(5,7,5,5,7))
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 1,
                    days = listOf(
                        DayData("Monday", 60, listOf(20,25,15,15,26)),
                        DayData("Tuesday", 45, listOf(18,10,13,13,10,10,9,27)),
                        DayData("Wednesday", 90, listOf(28,26,24,22,28)),
                        DayData("Thursday", 30, listOf(10,10,13,13,10,10,9,27)),
                        DayData("Friday", 120, listOf(30,28,26,24,30))
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 1,
                    days = listOf(
                        DayData("Monday", 60, listOf(51,34,45,42,51)),
                        DayData("Tuesday", 45, listOf(29,29,32,31,28,26,32,52)),
                        DayData("Wednesday", 90, listOf(52,38,48,46,53)),
                        DayData("Thursday", 30, listOf(25,25,28,28,25,22,20,54)),
                        DayData("Friday", 120, listOf(55,52,50,46,55))
                    )
                )
            }
            else -> {
                // Handle the case when level is not "level1", "level2", or "level3"
                weekData = null
            }
        }

        // Set click listeners for the days
        if(weekData != null){
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
        val action = ViewPagerFragmentDirections.actionViewPagerFragmentToSportFragment(weekData, index)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}