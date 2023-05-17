package com.ayd.pushapp.feature.pagerfragments

import android.os.Bundle
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


        // Create and set the WeekData instance
        val weekData = WeekData(
            weekNumber = 1,
            days = listOf(
                DayData("Monday", 60, listOf(5, 3, 2, 8, 3)),
                DayData("Tuesday", 45, listOf(1, 3, 2, 1, 4)),
                DayData("Wednesday", 30, listOf(4, 3, 9, 2, 3)),
                DayData("Thursday", 15, listOf(3, 3, 10, 1, 7)),
                DayData("Friday", 40, listOf(2, 3, 2, 6, 3))
            )
        )

        // Set click listeners for the days
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