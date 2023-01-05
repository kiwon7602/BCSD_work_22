package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rainbowFragment = RainbowFragment()
        val numberFragment = NumberFragment()
        val alphabetFragment = AlphabetFragment()

        val viewPager = findViewById<ViewPager2>(R.id.view_pager2)
        val rainbowAdapter = RainbowAdapter(this)
        val numberAdapter = NumberAdapter(this)
        val alphabetAdapter = AlphabetAdapter(this)

        rainbowAdapter.addColor()
        numberAdapter.addNumber()
        alphabetAdapter.addAlphabet()

        viewPager.adapter = rainbowAdapter
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.rainbow_tab -> viewPager.adapter = rainbowAdapter
                R.id.number_tab -> viewPager.adapter = numberAdapter
                R.id.alphabet_tab -> viewPager.adapter = alphabetAdapter
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.view_pager2, fragment)
            commit()
        }
    }
}