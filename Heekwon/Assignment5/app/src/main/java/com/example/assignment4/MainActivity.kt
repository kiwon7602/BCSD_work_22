package com.example.assignment4

import android.R.attr.level
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){
    private val fragment_sub = SubFragment()
    var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toastButton = findViewById<Button>(R.id.toastButton)
        val countButton = findViewById<Button>(R.id.countButton)
        val randomButton = findViewById<Button>(R.id.randomButton)
        val numberText = findViewById<TextView>(R.id.numberText)

        toastButton.setOnClickListener{
                val dialog = AlertDialog.Builder(this)
                    .setTitle("AlertDialog")
                    .setMessage("다이얼로그")
                    .setPositiveButton("positive") { _, _ ->
                        number = 0
                        numberText.text = number.toString()
                    }
                    .setNeutralButton("neutral") {_, _ ->
                        Toast.makeText(applicationContext, "Neutral", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("negative") { _, _ ->}
                    .show()
            }
        countButton.setOnClickListener{
                number += 1
                numberText.text = number.toString()
            }
        randomButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("number", number)
            fragment_sub.arguments = bundle
            supportFragmentManager.beginTransaction().apply {
                addToBackStack(null)
                replace(R.id.fragment_main, fragment_sub)
                commit()
            }
        }

        if(savedInstanceState == null){}
        else{
            number = savedInstanceState.getInt("number")
            numberText.text = number.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("number", number)
        super.onSaveInstanceState(outState)
    }
}