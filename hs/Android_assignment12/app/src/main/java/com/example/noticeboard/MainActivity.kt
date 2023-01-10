package com.example.noticeboard

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticeboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val myAdapter = NoticeAdapter()

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val addButton = binding.addNotice
        val recyclerView = binding.myRecyclerView
        var test = binding.test
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        test.text = myAdapter.itemCount.toString()

        recyclerView.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            this.adapter = myAdapter
        }

        addButton.setOnClickListener{
            val intent_main = Intent(this, AddNoticeActivity::class.java)
            startActivity(intent_main)
            //myAdapter.addNotice("가", "나", "다")
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