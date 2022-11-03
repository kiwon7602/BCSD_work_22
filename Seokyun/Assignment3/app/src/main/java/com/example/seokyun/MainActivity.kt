package com.example.seokyun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.constraint_login)

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val editID = findViewById<EditText>(R.id.editID)
        val editPW = findViewById<EditText>(R.id.editPW)
        val resultTxt = findViewById<TextView>(R.id.resultMsg)

        btnLogin.setOnClickListener(View.OnClickListener() {
            var result = editID.text.toString() + "님 환영합니다!"
            resultTxt.text = result
            var id = editID.text.toString()
            var password = editPW.text.toString()
            if(id == "Seokyun" && password == "bcsd22") {
                resultTxt.text = result
            }
            else resultTxt.text = "아이디와 비밀번호 중 하나가 틀렸습니다."
        })
    }
}