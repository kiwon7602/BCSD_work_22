package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NumberFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_number, container, false)
        val numberTextView: TextView = rootView.findViewById(R.id.number_text_view)

        val number = requireArguments().getInt("number", -1)
        numberTextView.text = number.toString()

        return rootView
    }
}