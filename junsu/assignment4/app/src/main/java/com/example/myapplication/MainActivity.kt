package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num = 0
        val numberTextView: TextView = findViewById(R.id.number_view)
        val dialogButton: Button = findViewById(R.id.dialog_button)
        val countButton: Button = findViewById(R.id.count_button)
        val randomButton: Button = findViewById(R.id.random_button)

        dialogButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)

            alertDialog.apply {
                setTitle(getString(R.string.dialog_title))
                setMessage(getString(R.string.dialog_message))
                setPositiveButton(R.string.reset) { _, _ ->
                    num = 0
                    numberTextView.text = num.toString()
                }
                setNeutralButton(R.string.toast) { dialog, _ ->
                    Toast.makeText(context, "print toast message", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                setNegativeButton(R.string.end, null)
            }

            alertDialog.show()
        }

        countButton.setOnClickListener {
            num++
            numberTextView.text = num.toString()
        }

        val startActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                num = it.data!!.getIntExtra("num", 0)
                numberTextView.text = num.toString()
            }
        }

        randomButton.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("num", num)
            startActivity.launch(intent)
        }
    }
}