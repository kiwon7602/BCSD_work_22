package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class NumberAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private val numberCollect = mutableListOf<Int>()

    override fun createFragment(position: Int): Fragment {
        val numberFragment = NumberFragment()
        val bundle = Bundle()

        bundle.putInt("number", numberCollect[position])
        numberFragment.arguments = bundle

        return numberFragment
    }

    override fun getItemCount(): Int = numberCollect.size

    fun addNumber() = (0..10).forEach { numberCollect.add(it) }
}