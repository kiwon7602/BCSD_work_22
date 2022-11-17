package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        val rainbowAdapter = RainbowAdapter(this)
        val numberAdapter = NumberAdapter(this)
        val alphabetAdapter = AlphabetAdapter(this)

        rainbowAdapter.addColor()
        numberAdapter.addNumber()
        alphabetAdapter.addAlphabet()

        viewPager.adapter = rainbowAdapter

        bottomNavigation.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.rainbow_menu -> viewPager.adapter = rainbowAdapter
                    R.id.number_menu -> viewPager.adapter = numberAdapter
                    R.id.alphabet_menu -> viewPager.adapter = alphabetAdapter
                }
                true
            }
        }
    }
}