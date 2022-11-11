package com.example.assignment4

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

class SubFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_sub, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val random = Random()
        val countNumber = arguments?.getInt("number")
        val numberText = view.findViewById<TextView>(R.id.numberText)
        val expText = view.findViewById<TextView>(R.id.explanationText)
        val randomNumber = random.nextInt(countNumber!!) + 1
        val bundle = Bundle()

        if (countNumber!! <= 0)
            numberText.text = countNumber.toString()
        else
            numberText.text = randomNumber.toString()
        expText.append(countNumber.toString())
        bundle.putInt("number", randomNumber)
        activity?.supportFragmentManager?.setFragmentResult("number", bundle)
    }
}