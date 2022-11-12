package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class SubActivity : AppCompatActivity(), NumData {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val fragment: Fragment = SubFragment()
        val num = intent.getIntExtra("num", 0)
        val bundle = Bundle().apply { putInt("num", num) }
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    private fun returnData(randomNum: Int) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("num", randomNum)
        }
        setResult(RESULT_OK, intent)
    }

    override fun countNum(randomNum: Int) {
        returnData(randomNum)
    }
}