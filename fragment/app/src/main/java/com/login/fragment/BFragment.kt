package com.login.fragment

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

public class BFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var count = 0
        arguments?.let {
            count = it.getInt("num")
        }
        val tvResult = view.findViewById<TextView>(R.id.result_tv)
        val tvRandom = view.findViewById<TextView>(R.id.random_tv)
        val random = (0..count).random()
        tvResult.setText("Random number between\n 0 ~ " + count.toString())
        tvRandom.text = random.toString()
    }
}