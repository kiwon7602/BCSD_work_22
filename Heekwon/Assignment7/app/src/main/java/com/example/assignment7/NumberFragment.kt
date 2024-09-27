package com.example.assignment7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text

class NumberFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position = requireArguments().getInt("positionB")
        val view: View = inflater.inflate(R.layout.fragment_number, container, false)
        view.findViewById<TextView>(R.id.number_id).text = position.toString()
        return view
    }
}
