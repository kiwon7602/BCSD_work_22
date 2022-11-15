package com.example.assignment5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val fragment_main = MainFragment()
    private val fragment_sub = SubFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, fragment_main)
            commit()
        }

   }

    fun changeFragment(index: Int, num: Int){

        when(index){
            1 -> {
                val bundle = Bundle()
                bundle.putInt("key", num)
                fragment_main.arguments = bundle; //프래그먼트에 값 보내줌
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.container, fragment_main)
                    commit()
                    }
             }

            2 -> {
                val bundle = Bundle()
                bundle.putInt("key", num)
                fragment_sub.arguments = bundle;
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.container, fragment_sub)
                    commit()
             }
         }
        }
   }
}