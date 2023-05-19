package com.ayd.pushapp.feature.pagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.data.database.AppDatabase
import com.ayd.pushapp.data.database.DayDataDao
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentSecondWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SecondWeek : Fragment() {

    private var _binding: FragmentSecondWeekBinding? = null
    private val binding get() = _binding!!

    private lateinit var dayDataDao: DayDataDao
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase.getInstance(requireContext())
        dayDataDao = db.dayDataDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSecondWeekBinding.inflate(inflater, container, false)

        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 2,
                    days = listOf(
                        DayData(15,"Monday", 60, listOf(6,8,6,5,7)),
                        DayData(16,"Tuesday", 45, listOf(6,6,5,4,8)),
                        DayData(17,"Wednesday", 90, listOf(9,6,8,7,9)),
                        DayData(18,"Thursday", 30, listOf(8,4,2,4,2,3,2,9)),
                        DayData(19,"Friday", 120, listOf(8,10,7,9,10))
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 2,
                    days = listOf(
                        DayData(20,"Monday", 60, listOf(31,21,18,14,32)),
                        DayData(21,"Tuesday", 45, listOf(20,12,15,15,10,8,6,33)),
                        DayData(22,"Wednesday", 90, listOf(29,33,29,29,34)),
                        DayData(23,"Thursday", 30, listOf(12,12,10,8,14,15,10,34)),
                        DayData(24,"Friday", 120, listOf(35,30,32,30,35))
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 2,
                    days = listOf(
                        DayData(25,"Monday", 60, listOf(56,50,45,40,56)),
                        DayData(26,"Tuesday", 45, listOf(34,32,32,28,30,34,34,57)),
                        DayData(27,"Wednesday", 90, listOf(57,52,50,44,58)),
                        DayData(28,"Thursday", 30, listOf(28,28,30,30,28,25,24,58)),
                        DayData(29,"Friday", 120, listOf(60,55,52,50,60))
                    )
                )
            }
            else -> {
                // Handle the case when level is not "level1", "level2", or "level3"
                weekData = null
            }
        }

        if (weekData != null) {
            for (i in weekData.days.indices) {
                val currentDay = weekData.days[i]
                lifecycleScope.launch {
                    val dayData = withContext(Dispatchers.IO) {
                        dayDataDao.getDayDataById(currentDay.id)
                    }
                    if (dayData != null) {
                        withContext(Dispatchers.Main) {
                            // Day data exists in the database, update UI accordingly
                            when (i) {
                                0 -> binding.day1.setBackgroundColor(R.drawable.button_design3)
                                1 -> binding.day2.setBackgroundColor(R.drawable.button_design3)
                                2 -> binding.day3.setBackgroundColor(R.drawable.button_design3)
                                3 -> binding.day4.setBackgroundColor(R.drawable.button_design3)
                                4 -> binding.day5.setBackgroundColor(R.drawable.button_design3)
                            }
                        }
                    }
                }
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