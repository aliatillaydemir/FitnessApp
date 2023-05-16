package com.ayd.pushapp.feature.mainfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ayd.pushapp.databinding.FragmentSportBinding
import com.ayd.pushapp.feature.ViewPagerFragmentDirections
import com.ayd.pushapp.model.WeekData
import kotlin.time.Duration.Companion.days


class SportFragment : Fragment() {

    private var _binding: FragmentSportBinding? = null
    private val binding get() = _binding!!

    //private var index: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSportBinding.inflate(inflater, container, false)

        val weekData = arguments?.getParcelable<WeekData>("weekData")
        val index = arguments?.getInt("index")

        weekData?.let {
            binding.textView.text = "Week Number: ${weekData.weekNumber}"
            binding.textView2.text = "Numbers: ${weekData.days[index!!].numbers}"
            binding.textView3.text = "Time value: ${weekData.days[index].timeValue} sec."
            binding.textView4.text = weekData.days[index].dayOfWeek
        }

        return binding.root
    }


}