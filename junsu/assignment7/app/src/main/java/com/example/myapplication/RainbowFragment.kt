package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RainbowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_rainbow, container, false)
        val colorTextView: TextView = rootView.findViewById(R.id.color_text_view)

        val color = requireArguments().getInt("color", -1)
        colorTextView.setBackgroundColor(color)

        return rootView
    }
}