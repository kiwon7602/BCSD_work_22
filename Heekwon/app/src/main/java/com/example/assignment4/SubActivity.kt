package com.example.assignment4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment4.databinding.ActivitySubBinding
import java.util.*

class SubActivity : AppCompatActivity(){

    lateinit var binding: ActivitySubBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val random = Random()
        val number = intent.getIntExtra("number", 9999)
        binding.numberText.text = (random.nextInt(number)+1).toString()
        binding.explanationText.append(number.toString())

    }
}