package com.example.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        requireActivity().supportFragmentManager.setFragmentResultListener(
            "number",
            viewLifecycleOwner
        ) { _, bundle ->
            val number = bundle.getInt("number")
            val countNumber = rootView.findViewById<TextView>(R.id.numberText)
            countNumber.text = number.toString()
            (activity as MainActivity).number = number
        }
        return (rootView)
    }
}