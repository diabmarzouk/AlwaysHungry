package com.cmpt362.alwayshungry

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class smartFridgeManager : AppCompatActivity() {

    // create Firebase authentication object
    private lateinit var authObj: FirebaseAuth

    private val TAB_TEXT = arrayOf("ADD", "ITEMS", "RECIPES")

    private lateinit var addFragment: addFragment
    private lateinit var itemsFragment: itemsFragment
    private lateinit var recipesFragment: recipesFragment
    private lateinit var fragments: ArrayList<Fragment>

    private lateinit var tab: TabLayout
    private lateinit var viewPager: ViewPager2

    var menuId : Int = 0

    private lateinit var myFragmentStateAdapter: FragmentStateAdapter
    private lateinit var tabLayoutMediator: TabLayoutMediator
    private lateinit var tabConfigurationStrategy: TabLayoutMediator.TabConfigurationStrategy


    // Populates the tabs in the main section of the app, ADD, ITEMS, and RECIPES
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.smart_fridge_manager)

        // Load users data from Firestore
        authObj = Firebase.auth

        val user = FirebaseAuth.getInstance().currentUser
//        user.uid

        addFragment = addFragment()
        itemsFragment = itemsFragment()
        recipesFragment = recipesFragment()

        fragments = ArrayList()
        fragments.add(addFragment)
        fragments.add(itemsFragment)
        fragments.add(recipesFragment)

        tab = findViewById(R.id.tab)
        viewPager = findViewById(R.id.viewpager)
        myFragmentStateAdapter = fragmentStateAdapter(this, fragments)
        viewPager.adapter = myFragmentStateAdapter

        tabConfigurationStrategy = TabLayoutMediator.TabConfigurationStrategy(){
                tab: TabLayout.Tab, position: Int ->
            tab.text = TAB_TEXT[position]
        }

        tabLayoutMediator = TabLayoutMediator(tab, viewPager, tabConfigurationStrategy)
        tabLayoutMediator.attach()

    }

    // Populates 3 dot menu in the fragment screen
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        menuId = item.itemId

        when{
            menuId == R.id.menuSettings -> startActivity(Intent(this, settingsActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }



}