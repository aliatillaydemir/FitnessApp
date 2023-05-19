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
import com.ayd.pushapp.databinding.FragmentFirstWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FirstWeek : Fragment() {

    private var _binding: FragmentFirstWeekBinding? = null
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
        _binding = FragmentFirstWeekBinding.inflate(inflater, container, false)

        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 1,
                    days = listOf(
                        DayData(0,"Monday", 60, listOf(2,3,2,2,3)),
                        DayData(1,"Tuesday", 45, listOf(3,4,2,3,4)),
                        DayData(2,"Wednesday", 90, listOf(4,5,4,4,5)),
                        DayData(3,"Thursday", 30, listOf(4,2,1,3,1,2,1,5)),
                        DayData(4,"Friday", 120, listOf(5,7,5,5,7))
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 1,
                    days = listOf(
                        DayData(5,"Monday", 60, listOf(20,25,15,15,26)),
                        DayData(6,"Tuesday", 45, listOf(18,10,13,13,10,10,9,27)),
                        DayData(7,"Wednesday", 90, listOf(28,26,24,22,28)),
                        DayData(8,"Thursday", 30, listOf(10,10,13,13,10,10,9,27)),
                        DayData(9,"Friday", 120, listOf(30,28,26,24,30))
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 1,
                    days = listOf(
                        DayData(10,"Monday", 60, listOf(51,34,45,42,51)),
                        DayData(11,"Tuesday", 45, listOf(29,29,32,31,28,26,32,52)),
                        DayData(12,"Wednesday", 90, listOf(52,38,48,46,53)),
                        DayData(13,"Thursday", 30, listOf(25,25,28,28,25,22,20,54)),
                        DayData(14,"Friday", 120, listOf(55,52,50,46,55))
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