package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.login_button)
        val googleButton = findViewById<ImageView>(R.id.google_logo)
        val naverButton = findViewById<ImageView>(R.id.naver_logo)
        val kakaoButton = findViewById<ImageView>(R.id.kakao_logo)

        loginButton.setOnClickListener()
        {
            Toast.makeText(getApplicationContext(),"준비중입니다", Toast.LENGTH_SHORT).show();
        }

        googleButton.setOnClickListener()
        {
            Toast.makeText(getApplicationContext(),"준비중입니다", Toast.LENGTH_SHORT).show();
        }

        kakaoButton.setOnClickListener()
        {
            Toast.makeText(getApplicationContext(),"준비중입니다", Toast.LENGTH_SHORT).show();
        }

        naverButton.setOnClickListener()
        {
            Toast.makeText(getApplicationContext(),"준비중입니다", Toast.LENGTH_SHORT).show();
        }
    }
}