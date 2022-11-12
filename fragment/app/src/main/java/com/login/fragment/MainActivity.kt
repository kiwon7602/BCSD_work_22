package com.login.fragment

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    val fragment_a = AFragment()
    val fragment_b = BFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.framelayout, fragment_a)
            commit()
        }
    }
    fun changeFragment(index: Int, num : Int){
        when(index){
            1 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout, fragment_a)
                    .commit()
            }

            2 -> {
                val bundle = Bundle()
                bundle.putInt("num", num)
                fragment_b.arguments = bundle
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout, fragment_b)
                    .commit()
            }
        }
    }
}