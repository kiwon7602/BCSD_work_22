package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputNameText: EditText = findViewById(R.id.input_name_text)
        val addNameButton: Button = findViewById(R.id.add_name_button)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val nameAdapter = NameAdapter()

        recyclerView.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = nameAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        addNameButton.setOnClickListener {
            if(inputNameText.text.isNotBlank()) {
                nameAdapter.addData(inputNameText.text.toString())
                inputNameText.setText("")
            }
            else Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show()
        }

        nameAdapter.setOnItemClickListener {
            val builder = AlertDialog.Builder(this).apply {
                setTitle(R.string.dialog_title)
                setMessage(R.string.dialog_message)
                setPositiveButton(R.string.delete) { _, _ ->
                    nameAdapter.removeData(it)
                }
                setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
            }

            builder.show()
        }

        nameAdapter.setOnItemLongClickListener {
            val inflater = layoutInflater
            val rootView = inflater.inflate(R.layout.edit_dialog, null)

            val builder = AlertDialog.Builder(this).apply {
                setTitle(R.string.dialog_title)
                setView(rootView)
                setPositiveButton(R.string.ok) { _, _ ->
                    val editNameText: TextView = rootView.findViewById(R.id.edit_name_text_view)

                    if(editNameText.text.isNotBlank()) {
                        nameAdapter.changeData(editNameText.text.toString(), it)
                    }
                    else Toast.makeText(context, R.string.toast_message, Toast.LENGTH_SHORT).show()
                }
                setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
            }

            builder.show()
        }
    }
}