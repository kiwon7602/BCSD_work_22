package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class RainbowFragment: Fragment(R.layout.fragment_rainbow){
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view = inflater.inflate(R.layout.fragment_rainbow, container, false)
         val colorText = view.findViewById<TextView>(R.id.color_view)

         val color = requireArguments().getInt("색깔", 0)
         colorText.setBackgroundColor(color)

         return view
    }

}