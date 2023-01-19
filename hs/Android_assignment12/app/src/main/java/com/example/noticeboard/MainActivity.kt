package com.example.noticeboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticeboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel : MainViewModel
    val myAdapter = NoticeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val addButton = binding.addNotice
        val recyclerView = binding.myRecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)

        recyclerView.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            this.adapter = myAdapter
            addItemDecoration(dividerItemDecoration)
        }

        val combineViewModel = CombineViewModel
        combineViewModel.viewModel = viewModel

        addButton.setOnClickListener{
            val intent_main = Intent(this, AddNoticeActivity::class.java)
            startActivity(intent_main)
        }

        myAdapter.setItemClickListener(object: NoticeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val builder = AlertDialog.Builder(this@MainActivity)
                    .setTitle("삭제")
                    .setMessage("삭제 하시겠습니다?")
                    .setPositiveButton("예") { _, _ ->
                        viewModel.removeData(position)
                    }
                    .setNegativeButton("아니오") { _ , _ -> null}
                    .show()
            }
        })

        myAdapter.setOnItemLongClickListener {
            val intent_main2 = Intent(this, ReviseNoticeActivity::class.java)
            intent_main2.putExtra("위치", it)
            startActivity(intent_main2)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}