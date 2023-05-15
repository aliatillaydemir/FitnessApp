package com.ayd.pushapp.feature.pagerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentMainBinding
import com.ayd.pushapp.databinding.FragmentSecondWeekBinding


class SecondWeek : Fragment() {

    private var _binding: FragmentSecondWeekBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSecondWeekBinding.inflate(inflater, container, false)

        binding.day1.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_sportFragment)
        }
        binding.day2.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_sportFragment)
        }
        binding.day3.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_sportFragment)
        }

        return binding.root
    }

}