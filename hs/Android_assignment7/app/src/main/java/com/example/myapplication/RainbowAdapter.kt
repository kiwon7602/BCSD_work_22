package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RainbowAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private val colorList = mutableListOf<Int>()

    override fun createFragment(position: Int): Fragment {
        val rainbowFragment = RainbowFragment()
        val bundle = Bundle()

        bundle.putInt("색깔", colorList[position])
        rainbowFragment.arguments = bundle

        return rainbowFragment
    }

    override fun getItemCount():  Int {
        return  colorList.size
    }

    fun addColor() {
        colorList.add(Color.parseColor("#FF0000"))
        colorList.add(Color.parseColor("#FFA500"))
        colorList.add(Color.parseColor("#FFFF00"))
        colorList.add(Color.parseColor("#00FF00"))
        colorList.add(Color.parseColor("#0000FF"))
        colorList.add(Color.parseColor("#2001A6"))
        colorList.add(Color.parseColor("#9400D3"))
        notifyDataSetChanged()
    }
}