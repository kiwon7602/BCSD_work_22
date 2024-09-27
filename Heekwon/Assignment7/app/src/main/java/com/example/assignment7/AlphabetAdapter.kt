package com.example.assignment7

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlphabetAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        val alphabetFragment = AlphabetFragment()
        val bundle = Bundle()
        bundle.putInt("positionC", position)
        alphabetFragment.arguments = bundle
        return alphabetFragment
    }

    override fun getItemCount(): Int {
        return 26
    }
}