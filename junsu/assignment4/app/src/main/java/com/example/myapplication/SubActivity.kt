package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
    }

    override fun onResume() {
        super.onResume()

        val randomNumTextView: TextView = findViewById(R.id.random_num_text)
        val explainTextView: TextView = findViewById(R.id.explain_text)

        val num = intent.getIntExtra("count", 0)
        val randomNum = Random.nextInt(num + 1)

        val explainText = getString(R.string.explain_message)  + "$randomNum"
        explainTextView.text = explainText
        randomNumTextView.text = randomNum.toString()
    }
}