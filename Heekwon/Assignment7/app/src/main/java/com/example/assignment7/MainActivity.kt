package com.example.assignment7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnv = findViewById<BottomNavigationView>(R.id.navigation)
        val viewpager2 = findViewById<ViewPager2>(R.id.viewPager2)
        val rainbowAdapter = RainbowAdapter(this)
        val numberAdapter = NumberAdapter(this)
        val alphabetAdapter = AlphabetAdapter(this)



        bnv.run { setOnItemSelectedListener {
            when(it.itemId){
                R.id.first->{
                    viewpager2.adapter = rainbowAdapter
                }
                R.id.second->{
                    viewpager2.adapter = numberAdapter
                }
                R.id.third->{
                    viewpager2.adapter = alphabetAdapter
                }
            }
            true
        }
            selectedItemId = R.id.first
        }
    }
}