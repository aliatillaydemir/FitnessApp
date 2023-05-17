package com.ayd.pushapp.feature.mainfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentSportBinding
import com.ayd.pushapp.model.WeekData
import com.ayd.pushapp.viewmodel.TimeCounterViewModel


class SportFragment : Fragment() {

    private var _binding: FragmentSportBinding? = null
    private val binding get() = _binding!!

    private lateinit var weekData: WeekData
    private lateinit var numbersList: List<Int>

    private var day_index: Int = 0
    private var list_index = 0

    private val timeViewModel: TimeCounterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSportBinding.inflate(inflater, container, false)

        weekData = arguments?.getParcelable("weekData")!!
        day_index = arguments?.getInt("index")!!

        numbersList = weekData.days[day_index].numbers

        binding.textView.text = "Week Number: ${weekData.weekNumber}"
        updateDisplayedNumber()

        binding.nextButton.setOnClickListener {
            if (list_index < numbersList.size - 1) {
                list_index++
                updateDisplayedNumber()
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

    private fun updateDisplayedNumber() {
        binding.textView2.text = "Numbers: ${numbersList[list_index]}"
        binding.timerTextView.text = "Countdown: ${weekData.days[day_index].timeValue} sec."
        binding.textView4.text = weekData.days[day_index].dayOfWeek

        //val counterValue = weekData.days[day_index].timeValue
        timeViewModel.isCounterRunning.observe(viewLifecycleOwner) { isRunning ->
            if (isRunning) {
                // Counter is running, disable next button and set it to red
                binding.nextButton.isEnabled = false
                binding.nextButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else {
                // Counter is not running, enable next button and set it to green
                binding.nextButton.isEnabled = true
                binding.nextButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
        }

    }

    private fun startCountDown(seconds: Int) {
        timeViewModel.startCountDown(seconds,
            onTick = {remainingSeconds ->
                binding.timerTextView.text = "Countdown: $remainingSeconds sec."
            },
            onFinish = {
                binding.timerTextView.text = "Countdown: 0 sec"
            })
    }

    private fun resetCountdown() {
        timeViewModel.resetCountdown()
        binding.timerTextView.text = "Countdown: ${weekData.days[day_index].timeValue} sec."
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}