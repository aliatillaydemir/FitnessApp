package com.ayd.pushapp.feature.mainfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentCongratsBinding
import com.ayd.pushapp.databinding.FragmentSixthWeekBinding


class CongratsFragment : Fragment() {

    private var _binding: FragmentCongratsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCongratsBinding.inflate(inflater, container, false)

        binding.goButton.setOnClickListener {
            findNavController().navigate(R.id.action_congratsFragment_to_mainFragment)
        }

        return binding.root
    }


}