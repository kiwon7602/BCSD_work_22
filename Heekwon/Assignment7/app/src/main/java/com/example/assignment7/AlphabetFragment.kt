package com.example.assignment7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AlphabetFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position = requireArguments().getInt("positionC")
        val abc = ArrayList<Char>()
        for (i in 'A' .. 'Z'){abc.add(i)}
        val view: View = inflater.inflate(R.layout.fragment_alphabet, container, false)
        view.findViewById<TextView>(R.id.alphabet_id).text = abc[position].toString()
        return view
    }
}
