package com.example.assignment7

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RainbowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position = requireArguments().getInt("positionA")
        val colorList = listOf<String>("#FF0000", "#FF9900", "#FFCC00", "#00FF00", "#3700B3", "#000099", "#990099")
        val view: View = inflater.inflate(R.layout.fragment_rainbow, container, false)
        view.setBackgroundColor(Color.parseColor(colorList[position]))
        return view
    }
}