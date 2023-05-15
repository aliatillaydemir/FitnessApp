package com.ayd.pushapp.adapter

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

    private val fragmentList = list

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
/*        return when(position){
            0 -> {
                FirstWeek()
            }
            1 -> {
                SecondWeek()
            }
            2 -> {
                ThirdWeek()
            }
            3 -> {
                FourthWeek()
            }
            4 ->{
                FifthWeek()
            }
            else -> {
                SixthWeek()
            }

        }*/
    }

}