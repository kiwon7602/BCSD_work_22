package com.example.assignment7

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RainbowAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        val rainbowFragment = RainbowFragment()
        val bundle = Bundle()
        bundle.putInt("positionA", position)
        rainbowFragment.arguments = bundle
        return rainbowFragment
    }

    override fun getItemCount(): Int {
        return 7
    }

}