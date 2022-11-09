package com.example.assignment5

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class SubFragment() :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sub, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var num = 0
        arguments?.let {
            num = it.getInt("key", 0)
        }

        val mActivity = activity as MainActivity
        val back_btn = view.findViewById<Button>(R.id.back_btn)
        val print_random_num = view.findViewById<TextView>(R.id.print_number)
        val print_message = view.findViewById<TextView>(R.id.print_message)
        val random = (1..num).random()

        print_message.setText("Here is a random\nnumber between 0\nand " + num.toString())
        print_random_num.setText(random.toString())
        back_btn.setOnClickListener{
            mActivity.changeFragment(1, random)
        }

}
}