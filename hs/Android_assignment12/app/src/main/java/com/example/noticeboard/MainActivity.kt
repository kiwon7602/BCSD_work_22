package com.example.noticeboard

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticeboard.databinding.ActivityMainBinding
import java.util.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel : MainViewModel
    val myAdapter = NoticeAdapter()

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val addButton = binding.addNotice
        val recyclerView = binding.myRecyclerView
        var test = binding.test
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        test.text = viewModel.get_size().toString()
        myAdapter.setNoticeItemList(viewModel.noticedata)

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

        if(intent.hasExtra("제목")) {
            myAdapter.addNotice(
                intent.getStringExtra("제목").toString(),
                intent.getStringExtra("내용").toString(),
                intent.getStringExtra("글쓴이").toString()
            )
        }

    }
}