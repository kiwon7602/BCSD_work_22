package com.example.assignment4

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val back_btn = findViewById<Button>(R.id.back_btn)
        val print_random_num = findViewById<TextView>(R.id.print_number)
        val print_message = findViewById<TextView>(R.id.print_message)
        val intent_sub = Intent(this, MainActivity::class.java)
        val num = intent.getIntExtra("숫자", 0)
        val random = (1..num).random()

        print_message.setText("Here is a random\nnumber between 0\nand " + num.toString())
        print_random_num.setText(random.toString())
        back_btn.setOnClickListener{
            intent_sub.putExtra("랜덤", random)
            startActivity(intent_sub)
        }
    }
}