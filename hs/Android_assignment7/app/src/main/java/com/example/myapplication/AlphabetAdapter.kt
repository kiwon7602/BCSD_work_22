package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlphabetAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private val alphabetList = mutableListOf<Char>()

    override fun createFragment(position: Int): Fragment {
        val alphabetFragment = AlphabetFragment()
        val bundle = Bundle()

        bundle.putChar("알파벳", alphabetList[position])
        alphabetFragment.arguments = bundle

        return alphabetFragment
    }

    override fun getItemCount(): Int {
        return  alphabetList.size
    }

    fun addAlphabet() = ('A'..'Z').forEach { alphabetList.add(it) }
}