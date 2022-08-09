package com.cmpt362.alwayshungry

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomAdapter(activity:FragmentActivity, var fragmentList:ArrayList<Fragment>) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}