package com.ayd.pushapp.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.ayd.pushapp.adapter.ViewPagerAdapter
import com.ayd.pushapp.databinding.FragmentViewPagerBinding
import com.ayd.pushapp.feature.pagerfragments.*

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater,container,false)

        val fragmentList = arrayListOf<Fragment>(
            FirstWeek(),
            SecondWeek(),
            ThirdWeek(),
            FourthWeek(),
            FifthWeek(),
            //SixthWeek() its just for level3
        )

        val level = arguments?.getString("level")  //its for viewPagerFragment(how much week?)

        if(level == "level3"){
            fragmentList.add(SixthWeek())
        }

        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.indicator.attachTo(binding.viewPager)

        // Pass the level argument to the adapter
        adapter.setLevel(arguments?.getString("level")) //its for week1, week2, week3...(weeks in ViewPagerFragment. So its for adapter)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}