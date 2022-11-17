package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RainbowAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private val colorCollect = mutableListOf<Int>()

    override fun createFragment(position: Int): Fragment {
        val rainbowFragment = RainbowFragment()
        val bundle = Bundle()

        bundle.putInt("color", colorCollect[position])
        rainbowFragment.arguments = bundle

        return rainbowFragment
    }

    override fun getItemCount(): Int = colorCollect.size

    fun addColor() {
        colorCollect.add(Color.parseColor("#FF0000"))
        colorCollect.add(Color.parseColor("#FFA500"))
        colorCollect.add(Color.parseColor("#FFFF00"))
        colorCollect.add(Color.parseColor("#008000"))
        colorCollect.add(Color.parseColor("#0000FF"))
        colorCollect.add(Color.parseColor("#4B0082"))
        colorCollect.add(Color.parseColor("#800080"))
        notifyDataSetChanged()
    }
}