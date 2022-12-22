package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NumberFragment: Fragment(R.layout.fragment_number){
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_number, container, false)
        val numberTextView= view.findViewById<TextView>(R.id.number_text)

        val num = requireArguments().getInt("숫자", 0)
        numberTextView.setText(num.toString())

        return view
    }
}