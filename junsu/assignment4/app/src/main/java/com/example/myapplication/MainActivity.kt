package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var getRandomNum: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num = 0
        val numberTextView: TextView = findViewById(R.id.number_view)
        val toastButton: Button = findViewById(R.id.toast_button)
        val countButton: Button = findViewById(R.id.count_button)
        val randomButton: Button = findViewById(R.id.random_button)

        toastButton.setOnClickListener {
            Toast.makeText(applicationContext, getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
        }

        countButton.setOnClickListener {
            num++
            numberTextView.text = num.toString()
        }

        getRandomNum = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                val randomNum = it.data?.getIntExtra("num", -1) ?: ""
                num = randomNum.toString().toInt()
                numberTextView.text = num.toString()
            }
        }

        randomButton.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java).apply {
                putExtra("num", num)
            }
            getRandomNum.launch(intent)
        }
    }
}