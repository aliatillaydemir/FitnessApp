package com.ayd.pushapp.feature.mainfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.ayd.pushapp.R
import com.ayd.pushapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val DARK_MODE_KEY = "dark_mode_key"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        // Set the initial state of the switch based on stored preference
        binding.darkModeSwitch.isChecked = sharedPreferences.getBoolean(DARK_MODE_KEY, false)

        // Set an OnCheckedChangeListener to handle switch state changes
        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
        }

        binding.buttonlvl1.setOnClickListener {
            val bundle = Bundle().apply {
                putString("level", "level1")
            }
            findNavController().navigate(R.id.action_mainFragment_to_viewPagerFragment, bundle)
        }

        binding.buttonlvl2.setOnClickListener {
            val bundle = Bundle().apply {
                putString("level", "level2")
            }
            findNavController().navigate(R.id.action_mainFragment_to_viewPagerFragment, bundle)
        }

        binding.buttonlvl3.setOnClickListener {
            val bundle = Bundle().apply {
                putString("level", "level3")
            }
            findNavController().navigate(R.id.action_mainFragment_to_viewPagerFragment, bundle)
        }

        return binding.root
    }


    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        binding.darkModeSwitch.isChecked = true

        // Store the dark mode preference
        editor.putBoolean(DARK_MODE_KEY, true)
        editor.apply()

    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.darkModeSwitch.isChecked = false

        // Store the dark mode preference
        editor.putBoolean(DARK_MODE_KEY, false)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}