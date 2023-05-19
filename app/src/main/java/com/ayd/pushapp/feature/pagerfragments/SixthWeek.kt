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
import com.ayd.pushapp.databinding.FragmentFourthWeekBinding
import com.ayd.pushapp.databinding.FragmentSixthWeekBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SixthWeek : Fragment() {

    private var _binding: FragmentSixthWeekBinding? = null
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
        _binding = FragmentSixthWeekBinding.inflate(inflater, container, false)


        val level = requireArguments().getString("level")
        val weekData: WeekData?

        when (level) {
            "level1" -> {
                weekData = WeekData(
                    weekNumber = 6,
                    days = listOf(
                        DayData(76,"Monday", 60, listOf()),
                        DayData(77,"Tuesday", 45, listOf()),
                        DayData(78,"Wednesday", 90, listOf()),
                        DayData(79,"Thursday", 30, listOf()),
                        DayData(80,"Friday", 120, listOf())
                    )
                )
            }
            "level2" -> {
                weekData = WeekData(
                    weekNumber = 6,
                    days = listOf(
                        DayData(81,"Monday", 60, listOf()),
                        DayData(82,"Tuesday", 45, listOf()),
                        DayData(83,"Wednesday", 90, listOf()),
                        DayData(84,"Thursday", 30, listOf()),
                        DayData(85,"Friday", 120, listOf())
                    )
                )
            }
            "level3" -> {
                weekData = WeekData(
                    weekNumber = 6,
                    days = listOf(
                        DayData(86,"Monday", 60, listOf(91,80,78,78,72,71,93)),
                        DayData(87,"Tuesday", 45, listOf(55,52,50,50,48,46,44,95)),
                        DayData(88,"Wednesday", 90, listOf(95,90,85,82,80,70,97)),
                        DayData(89,"Thursday", 30, listOf(60,50,48,48,46,44,42,98)),
                        DayData(90,"Friday", 120, listOf(100,95,92,90,100))
                    )
                )
            }
            else -> {
                // Handle the case when level is not "level1", "level2", or "level3"
                weekData = null
            }
        }

        binding.delete1.setOnClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(0)?.id
            showDialogBox(0,dayIdToDelete!!)
        }
        binding.delete2.setOnClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(1)?.id
            showDialogBox(1,dayIdToDelete!!)
        }
        binding.delete3.setOnClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(2)?.id
            showDialogBox(2,dayIdToDelete!!)
        }
        binding.delete4.setOnClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(3)?.id
            showDialogBox(3,dayIdToDelete!!)
        }
        binding.delete5.setOnClickListener {
            val dayIdToDelete = weekData?.days?.getOrNull(4)?.id
            showDialogBox(4,dayIdToDelete!!)
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

    private fun showDialogBox(btnIndex: Int, dayIdToDelete: Int) {

        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yesButton: TextView = dialog.findViewById(R.id.yesButton)
        val noButton: TextView = dialog.findViewById(R.id.noButton)

        yesButton.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    dayDataDao.deleteDayDataById(dayIdToDelete)
                }
                withContext(Dispatchers.Main){
                    when(btnIndex){
                        0 -> {
                            binding.white1.visibility = View.VISIBLE
                            binding.green1.visibility = View.INVISIBLE
                        }
                        1 -> {
                            binding.white2.visibility = View.VISIBLE
                            binding.green2.visibility = View.INVISIBLE
                        }
                        2 -> {
                            binding.white3.visibility = View.VISIBLE
                            binding.green3.visibility = View.INVISIBLE
                        }
                        3 -> {
                            binding.white4.visibility = View.VISIBLE
                            binding.green4.visibility = View.INVISIBLE
                        }
                        4 -> {
                            binding.white5.visibility = View.VISIBLE
                            binding.green5.visibility = View.INVISIBLE
                        }
                    }
                    Toast.makeText(context,"record deleted", Toast.LENGTH_SHORT).show()

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