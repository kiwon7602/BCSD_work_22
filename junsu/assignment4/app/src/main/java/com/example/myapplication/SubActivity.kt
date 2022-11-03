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

        val intent = Intent(this, MainActivity::class.java).apply {
            val num = intent.getIntExtra("num", -1)
            val randomNum = Random.nextInt(num + 1)

            randomNumTextView.text = randomNum.toString()
            val explainText = getString(R.string.explain_message) + "$randomNum"
            explainTextView.text = explainText

            putExtra("num", randomNum)
        }
        setResult(RESULT_OK, intent)
    }
}