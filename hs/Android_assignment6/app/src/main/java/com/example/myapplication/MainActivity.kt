package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName = findViewById<EditText>(R.id.input_text)
        val addButton = findViewById<Button>(R.id.plus_button)
        val recyclerView: RecyclerView = findViewById(R.id.my_recycler_view)
        val myAdapter = MyAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            this.adapter = myAdapter
        }

        addButton.setOnClickListener{
            myAdapter.addName(inputName.text.toString())
            inputName.setText("")
        }

        myAdapter.setOnItemClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle("삭제")
                .setMessage("삭제 하시겠습니다?")
                .setPositiveButton("예") { _, _ ->
                    myAdapter.deleteName(it)
                }
                .setNegativeButton("아니오") { _ , _ -> null}
                .show()
        }

        myAdapter.setOnItemLongClickListener {
            val inflater = layoutInflater
            val rootView = inflater.inflate(R.layout.revise_name_dialog, null)

            val builder = AlertDialog.Builder(this)
                .setTitle("수정")
                .setView(rootView)
                .setPositiveButton("예") { _, _ ->
                    val editNameText: TextView = rootView.findViewById(R.id.edit_name_text_view)
                    myAdapter.reviseData(editNameText.text.toString(), it)
                }
                .setNegativeButton("취소") { _, _ -> null}
                .show()
        }
    }
}