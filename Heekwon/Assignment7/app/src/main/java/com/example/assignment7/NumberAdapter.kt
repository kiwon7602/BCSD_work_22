package com.example.assignment7

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class NumberAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        val numberFragment = NumberFragment()
        val bundle = Bundle()
        bundle.putInt("positionB", position)
        numberFragment.arguments = bundle
        return numberFragment
    }

    override fun getItemCount(): Int {
        return 11
    }
}