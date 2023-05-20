package com.ayd.pushapp.feature.pagerfragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.data.database.AppDatabase
import com.ayd.pushapp.data.database.DayDataDao
import com.ayd.pushapp.databinding.FragmentFifthWeekBinding
import com.ayd.pushapp.databinding.FragmentFirstWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FifthWeek : Fragment() {

    private var _binding: FragmentFifthWeekBinding? = null
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
        _binding = FragmentFifthWeekBinding.inflate(inflater, container, false)


        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 5,
                    days = listOf(
                        DayData(61,"Monday", 60, listOf(20,18,16,14,20)),
                        DayData(62,"Tuesday", 45, listOf(15,19,14,13,22)),
                        DayData(63,"Wednesday", 90, listOf(17,22,16,15,22)),
                        DayData(64,"Thursday", 30, listOf(21,12,10,8,6,5,4,23)),
                        DayData(65,"Friday", 120, listOf(25,23,22,20,25))
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 5,
                    days = listOf(
                        DayData(66,"Monday", 60, listOf(46,43,29,41,46)),
                        DayData(67,"Tuesday", 45, listOf(25,25,26,26,24,24,22,47)),
                        DayData(68,"Wednesday", 90, listOf(50,48,45,45,48)),
                        DayData(69,"Thursday", 30, listOf(23,23,25,25,22,22,20,48)),
                        DayData(70,"Friday", 120, listOf(50,48,46,44,50))
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 5,
                    days = listOf(
                        DayData(71,"Monday", 60, listOf(81,60,64,66,68,70,83)),
                        DayData(72,"Tuesday", 45, listOf(45,42,40,40,38,36,36,85)),
                        DayData(73,"Wednesday", 90, listOf(85,80,75,72,70,65,87)),
                        DayData(74,"Thursday", 30, listOf(45,40,38,38,36,34,32,88)),
                        DayData(75,"Friday", 120, listOf(90,85,82,80,90))
                    )
                )
            }
            else -> {
                // Handle the case when level is not "level1", "level2", or "level3"
                weekData = null
            }
        }

        binding.cardViewDay1.setOnLongClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(0)?.id
            toggleDayData(dayIdToDelete, binding.white1, binding.green1, weekData)
            true
        }
        binding.cardViewDay2.setOnLongClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(1)?.id
            toggleDayData(dayIdToDelete, binding.white2, binding.green2, weekData)
            true
        }
        binding.cardViewDay3.setOnLongClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(2)?.id
            toggleDayData(dayIdToDelete, binding.white3, binding.green3, weekData)
            true
        }
        binding.cardViewDay4.setOnLongClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(3)?.id
            toggleDayData(dayIdToDelete, binding.white4, binding.green4, weekData)
            true
        }
        binding.cardViewDay5.setOnLongClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(4)?.id
            toggleDayData(dayIdToDelete, binding.white5, binding.green5, weekData)
            true
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
                                0 ->{
                                    binding.white1.visibility = View.INVISIBLE
                                    binding.green1.visibility = View.VISIBLE
                                }
                                1 -> {
                                    binding.white2.visibility = View.INVISIBLE
                                    binding.green2.visibility = View.VISIBLE
                                }
                                2 -> {
                                    binding.white3.visibility = View.INVISIBLE
                                    binding.green3.visibility = View.VISIBLE
                                }
                                3 -> {
                                    binding.white4.visibility = View.INVISIBLE
                                    binding.green4.visibility = View.VISIBLE
                                }
                                4 -> {
                                    binding.white5.visibility = View.INVISIBLE
                                    binding.green5.visibility = View.VISIBLE
                                }
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


    private fun toggleDayData(dayId: Int?, whiteView: View, greenView: View, weekData: WeekData?) {
        if (dayId != null && weekData != null) {
            lifecycleScope.launch {
                val dayData = withContext(Dispatchers.IO) {
                    dayDataDao.getDayDataById(dayId)
                }
                if (dayData != null) {
                    // Day data exists in the database, show dialog box to delete
                    showDialogBox(dayId, whiteView, greenView)
                } else {
                    // Day data doesn't exist, insert into the database
                    val dayData = weekData.days.find { it.id == dayId }
                    if (dayData != null) {
                        val dayDataEntity = DayData(
                            dayData.id,
                            dayData.dayOfWeek,
                            dayData.timeValue,
                            dayData.numbers
                        )
                        withContext(Dispatchers.IO) {
                            dayDataDao.insert(dayDataEntity)
                        }
                        whiteView.visibility = View.INVISIBLE
                        greenView.visibility = View.VISIBLE
                        Toast.makeText(context, "Record inserted", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showDialogBox(dayIdToDelete: Int, whiteView: View, greenView: View) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesButton: TextView = dialog.findViewById(R.id.yesButton)
        val noButton: TextView = dialog.findViewById(R.id.noButton)

        yesButton.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    dayDataDao.deleteDayDataById(dayIdToDelete)
                }
                withContext(Dispatchers.Main) {
                    whiteView.visibility = View.VISIBLE
                    greenView.visibility = View.INVISIBLE
                    Toast.makeText(context, "Record deleted", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }

        noButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
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