package com.cmpt362.alwayshungry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class settingsActivity : AppCompatActivity() {

    private lateinit var settingsFragment: SettingsFragment
    private lateinit var fragments: ArrayList<Fragment>
    private lateinit var viewPager: ViewPager2
    private lateinit var myFragmentStateAdapter: FragmentStateAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.settings_layout)
        settingsFragment = SettingsFragment()

        fragments = ArrayList()
        fragments.add(settingsFragment)
        viewPager = findViewById(R.id.viewpager)
        myFragmentStateAdapter = fragmentStateAdapter(this, fragments)
        viewPager.adapter = myFragmentStateAdapter
    }

}