package com.example.assignment12

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment12.databinding.ActivityModifyBinding

class ModifyPostActivity : AppCompatActivity() {

    private lateinit var binding : ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addButton.setOnClickListener {
            val returnIntent = Intent(this, MainActivity::class.java)
            returnIntent.putExtra("Title", binding.editTextTitle.text.toString())
            returnIntent.putExtra("Content", binding.editTextContent.text.toString())
            returnIntent.putExtra("User", binding.editTextUser.text.toString())
            setResult(2, returnIntent)
            finish()
        }
    }

}