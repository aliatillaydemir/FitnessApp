package com.ayd.pushapp.feature.mainfragments

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.data.database.AppDatabase
import com.ayd.pushapp.data.database.DayDataDao
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentSportBinding
import com.ayd.pushapp.model.DayData
import com.ayd.pushapp.model.WeekData
import com.ayd.pushapp.viewmodel.TimeCounterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SportFragment : Fragment() {

    private var _binding: FragmentSportBinding? = null
    private val binding get() = _binding!!

    private lateinit var weekData: WeekData
    private lateinit var numbersList: List<Int>

    private var day_index: Int = 0
    private var list_index: Int = 0

    private val timeViewModel: TimeCounterViewModel by viewModels()

    private lateinit var dayDataDao: DayDataDao
    private lateinit var db: AppDatabase

    private lateinit var progressBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSportBinding.inflate(inflater, container, false)

        db = AppDatabase.getInstance(requireContext())
        dayDataDao = db.dayDataDao()

        weekData = arguments?.getParcelable("weekData")!!
        day_index = arguments?.getInt("index")!!

        numbersList = weekData.days[day_index].numbers

        progressBar = binding.progressBar

        binding.textView.text = "(Week ${weekData.weekNumber})"
        updateDisplayedNumber()

        binding.nextButton.setOnClickListener {
            if (list_index < numbersList.size - 1) {
                list_index++
                updateDisplayedNumber()
            }else{
                // Insert data into the database
                val dayData = weekData.days[day_index]
                val dayDataEntity = DayData(dayData.id, dayData.dayOfWeek, dayData.timeValue, dayData.numbers)

                insertDayData(dayDataEntity)
            }
        }

        binding.backButton.setOnClickListener {
            if (list_index > 0) {
                list_index--
                updateDisplayedNumber()
            }else{
                //nothing
            }
        }

        binding.counterButton.setOnClickListener {
            startCountDown(weekData.days[day_index].timeValue)
        }

        binding.resetButton.setOnClickListener {
            resetCountdown()
        }

        return binding.root
    }

    private fun insertDayData(dayDataEntity: DayData) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                dayDataDao.insert(dayDataEntity)
            }
            // Navigation logic after inserting data
            findNavController().navigate(R.id.action_sportFragment_to_congratsFragment)
        }
    }

    private fun updateDisplayedNumber() {
        binding.textView2.text = "${weekData.days[day_index].numbers[list_index]}"
        binding.timerTextView.text = "${weekData.days[day_index].timeValue}"
        binding.textView4.text = weekData.days[day_index].dayOfWeek
        binding.stepText.text = "${list_index+1}/${numbersList.size}"

        val progress = weekData.days[day_index].timeValue * 100
        progressBar.progress = progress

        val teal = ContextCompat.getColor(requireContext(), R.color.teal_200)
        progressBar.progressTintList = ColorStateList.valueOf(teal)

        binding.timerTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
        binding.remainingTimeText.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_200))


        //val counterValue = weekData.days[day_index].timeValue
        timeViewModel.isCounterRunning.observe(viewLifecycleOwner) { isRunning ->
            if (isRunning) {
                // Counter is running, disable next button and set it to red
                binding.nextButton.isEnabled = false
                binding.nextButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))

                binding.backButton.isEnabled = false
                binding.backButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else {
                // Counter is not running, enable next button and set it to green
                binding.nextButton.isEnabled = true
                binding.nextButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))

                binding.backButton.isEnabled = true
                binding.backButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
        }

    }

    private fun startCountDown(seconds: Int) {
        timeViewModel.startCountDown(seconds,
            onTick = {remainingSeconds ->
                binding.timerTextView.text = "$remainingSeconds"
                val progress = (remainingSeconds.toFloat() / weekData.days[day_index].timeValue.toFloat() * 100).toInt()
                progressBar.progress = progress

                if (remainingSeconds <= 10) {
                    val red = ContextCompat.getColor(requireContext(), R.color.red)
                    progressBar.progressTintList = ColorStateList.valueOf(red)

                    binding.timerTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    binding.remainingTimeText.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                }else{
                    val teal = ContextCompat.getColor(requireContext(), R.color.teal_200)
                    progressBar.progressTintList = ColorStateList.valueOf(teal)

                    binding.timerTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
                    binding.remainingTimeText.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
                }

            },
            onFinish = {
                binding.timerTextView.text = "0"
            })
    }

    private fun resetCountdown() {
        timeViewModel.resetCountdown()
        binding.timerTextView.text = "${weekData.days[day_index].timeValue}"
        progressBar.progress = 100

        val teal = ContextCompat.getColor(requireContext(), R.color.teal_200)
        progressBar.progressTintList = ColorStateList.valueOf(teal)

        binding.timerTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
        binding.remainingTimeText.setTextColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}