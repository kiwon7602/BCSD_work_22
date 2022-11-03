package com.example.assignment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.assignment4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toastButton.setOnClickListener(this)
        binding.countButton.setOnClickListener(this)
        binding.randomButton.setOnClickListener(this)

    }

    override fun onClick(v: View?){
        val intent = Intent(this, SubActivity::class.java)
        var number = Integer.parseInt(binding.numberText.text.toString())
        when(v?.id){
            binding.toastButton.id->
                Toast.makeText(this, "시공의 폭풍은 정말 최고야", Toast.LENGTH_SHORT).show()
            binding.countButton.id-> {
                number += 1
                binding.numberText.text = number.toString()
            }
            binding.randomButton.id->{
                intent.putExtra("number", number)
                startActivity(intent)
            }
        }
    }

}