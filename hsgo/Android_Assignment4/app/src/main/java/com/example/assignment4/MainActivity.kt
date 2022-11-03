package com.example.assignment4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("LifeCycle", "onCreate 호출됨")

        var num = 0
        val print_num = findViewById<TextView>(R.id.print_number)
        val plus_number = findViewById<Button>(R.id.count_btn)
        val toast_massage = findViewById<Button>(R.id.tst_btn)
        val random_btn = findViewById<Button>(R.id.random_btn)

        if(intent.hasExtra("랜덤"))
        {
            num = intent.getIntExtra("랜덤", 0)
            print_num.setText(num.toString())
        }

        toast_massage.setOnClickListener()
        {
            Toast.makeText(applicationContext,"현재 입력된 숫자는 " + num.toString() + "입니다.", Toast.LENGTH_SHORT).show();
        }

        plus_number.setOnClickListener{
            num++
            print_num.setText(num.toString())
        }

        random_btn.setOnClickListener{
            val intent_main = Intent(this, SubActivity::class.java)
            intent_main.putExtra("숫자", num)
            startActivity(intent_main)
        }
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("LifeCycle", "onRestart 호출됨")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "onStart 호출됨")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "onResume 호출됨")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "onPause 호출됨")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "onStop 호출됨")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "onDestroy 호출됨")
    }
}