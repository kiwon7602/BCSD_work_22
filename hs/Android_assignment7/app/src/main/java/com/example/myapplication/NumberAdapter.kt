package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class NumberAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private val numberList = mutableListOf<Int>()

    override fun createFragment(position: Int): Fragment {
        val numberFragment = NumberFragment()
        val bundle = Bundle()

        bundle.putInt("숫자", numberList[position])
        numberFragment.arguments = bundle

        return numberFragment
    }

    override fun getItemCount():  Int {
        return  numberList.size
    }
    fun addNumber() = (0..9).forEach { numberList.add(it) }
}