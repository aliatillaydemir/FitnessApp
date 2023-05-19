package com.ayd.pushapp.feature.pagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.data.database.AppDatabase
import com.ayd.pushapp.data.database.DayDataDao
import com.ayd.pushapp.databinding.FragmentFirstWeekBinding
import com.ayd.pushapp.databinding.FragmentFourthWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FourthWeek : Fragment() {

    private var _binding: FragmentFourthWeekBinding? = null
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
        _binding = FragmentFourthWeekBinding.inflate(inflater, container, false)


        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 4,
                    days = listOf(
                        DayData(46,"Monday", 60, listOf(16,12,10,8,16)),
                        DayData(47,"Tuesday", 45, listOf(16,11,10,8,17)),
                        DayData(48,"Wednesday", 90, listOf(18,12,14,10,18)),
                        DayData(49,"Thursday", 30, listOf(16,10,8,4,10,6,2,18)),
                        DayData(50,"Friday", 120, listOf(20,18,16,15,20))
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 4,
                    days = listOf(
                        DayData(51,"Monday", 60, listOf(41,24,38,36,41)),
                        DayData(52,"Tuesday", 45, listOf(19,19,22,22,18,18,22,42)),
                        DayData(53,"Wednesday", 90, listOf(42,28,38,38,43)),
                        DayData(54,"Thursday", 30, listOf(15,15,18,18,15,12,10,44)),
                        DayData(55,"Friday", 120, listOf(45,42,40,36,45))
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 4,
                    days = listOf(
                        DayData(56,"Monday", 60, listOf(71,60,58,56,54,50,73)),
                        DayData(57,"Tuesday", 45, listOf(40,38,38,35,36,36,34,75)),
                        DayData(58,"Wednesday", 90, listOf(75,70,65,63,60,52,77)),
                        DayData(59,"Thursday", 30, listOf(40,38,36,32,35,32,30,78)),
                        DayData(60,"Friday", 120, listOf(80,75,82,65,80))
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