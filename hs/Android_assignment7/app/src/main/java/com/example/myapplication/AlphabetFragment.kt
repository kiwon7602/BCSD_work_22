package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class AlphabetFragment: Fragment(R.layout.fragment_alphabet) {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alphabet, container, false)
        val alphabetTextView = view.findViewById<TextView>(R.id.alphabet_text)

        val alphabet = requireArguments().getChar("알파벳")
        alphabetTextView.setText(alphabet.toString())

        return view
    }
}