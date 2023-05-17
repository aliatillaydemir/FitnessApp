package com.ayd.pushapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ayd.pushapp.feature.pagerfragments.*

class ViewPagerAdapter(
    list: ArrayList<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {

    private var level: String? = null

    fun setLevel(level: String?) {
        this.level = level
    }

    private val fragmentList = list

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        /*return fragmentList[position]*/
        return when(position){
            0 -> {
                val fragment = FirstWeek()
                val args = Bundle().apply {
                    putString("level", level)
                }
                fragment.arguments = args
                fragment
            }
            1 -> {
                val fragment = SecondWeek()
                val args = Bundle().apply {
                    putString("level", level)
                }
                fragment.arguments = args
                fragment
            }
            2 -> {
                val fragment = ThirdWeek()
                val args = Bundle().apply {
                    putString("level", level)
                }
                fragment.arguments = args
                fragment
            }
            3 -> {
                val fragment = FourthWeek()
                val args = Bundle().apply {
                    putString("level", level)
                }
                fragment.arguments = args
                fragment
            }
            4 ->{
                val fragment = FifthWeek()
                val args = Bundle().apply {
                    putString("level", level)
                }
                fragment.arguments = args
                fragment
            }
            5 -> {
                val fragment = SixthWeek()
                val args = Bundle().apply {
                    putString("level", level)
                }
                fragment.arguments = args
                fragment
            }
            else ->{
                throw IllegalArgumentException("Invalid position: $position")
            }

        }
    }

}