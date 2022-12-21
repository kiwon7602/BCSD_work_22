package com.example.assignment8

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment8.databinding.ActivitySubBinding
import java.util.*

class SubActivity : AppCompatActivity(){

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val numberText = findViewById<TextView>(R.id.numberText)
        var explanationText = findViewById<TextView>(R.id.explanationText)

        val intent = Intent(applicationContext, MainActivity::class.java).apply{
            val random = Random()
            val number = intent.getIntExtra("number", -1)
            val randomNumber = random.nextInt(number)+1
            numberText.text= randomNumber.toString()
            explanationText.append(number.toString())
            putExtra("number",randomNumber)
        }
        setResult(RESULT_OK, intent)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}