package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class SubFragment : Fragment() {
    private val numData get() = context as NumData

    override fun onAttach(context: Context) {
        super.onAttach(context)
        numData
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_sub, container, false)
        val explainText: TextView = rootView.findViewById(R.id.explain_text)
        val randomNumText: TextView = rootView.findViewById(R.id.random_num_text)
        val receiveNum = requireArguments().getInt("num", 0)
        val randomNum = Random.nextInt(receiveNum)

        explainText.text = getString(R.string.explain_message) + " " + receiveNum
        randomNumText.text = randomNum.toString()
        sendNum(randomNum)

        return rootView
    }

    private fun sendNum(randomNum: Int) {
        numData.countNum(randomNum)
    }
}