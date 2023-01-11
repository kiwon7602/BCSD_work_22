package com.example.noticeboard

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        val myAdapter = NoticeAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        test.text = viewModel.get_size().toString()

        recyclerView.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            this.adapter = myAdapter
            myAdapter.setNoticeItemList(viewModel.get_list())
        }

        addButton.setOnClickListener{
            //val intent_main = Intent(this, AddNoticeActivity::class.java)
            //startActivity(intent_main)
            //myAdapter.addNotice("가", "나", "다")
            val inflater = layoutInflater
            val rootView = inflater.inflate(R.layout.add_notice, null)

            val builder = AlertDialog.Builder(this)
                .setTitle("추가")
                .setView(rootView)
                .setPositiveButton("예") { _, _ ->
                    val inputTitle:TextView = rootView.findViewById(R.id.notice_title)
                    val inputContent:TextView = rootView.findViewById(R.id.notice_content)
                    val inputName:TextView = rootView.findViewById(R.id.notice_writer)
                    myAdapter.addNotice(inputTitle.text.toString(), inputContent.text.toString(), inputName.text.toString())
                }
                .setNegativeButton("취소") { _, _ -> null}
                .show()
        }

        myAdapter.setItemClickListener(object: NoticeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val builder = AlertDialog.Builder(this@MainActivity)
                    .setTitle("삭제")
                    .setMessage("삭제 하시겠습니다?")
                    .setPositiveButton("예") { _, _ ->
                        myAdapter.deleteName(position)
                    }
                    .setNegativeButton("아니오") { _ , _ -> null}
                    .show()
            }
        })

        myAdapter.setOnItemLongClickListener {
            val inflater = layoutInflater
            val rootView = inflater.inflate(R.layout.add_notice, null)

            val builder = AlertDialog.Builder(this)
                .setTitle("수정")
                .setView(rootView)
                .setPositiveButton("예") { _, _ ->
                    val inputTitle:TextView = rootView.findViewById(R.id.notice_title)
                    val inputContent:TextView = rootView.findViewById(R.id.notice_content)
                    val inputName:TextView = rootView.findViewById(R.id.notice_writer)

                    //inputTitle.text = myAdapter.dataSet[it].title
                    //inputContent.text = myAdapter.dataSet[it].content
                    //inputName.text = myAdapter.dataSet[it].name
                    myAdapter.reviseData(inputTitle.text.toString(), inputContent.text.toString(),inputName.text.toString(), it )
                }
                .setNegativeButton("취소") { _, _ -> null}
                .show()
        }

        /*if(intent.hasExtra("제목")) {
            myAdapter.addNotice(
                intent.getStringExtra("제목").toString(),
                intent.getStringExtra("내용").toString(),
                intent.getStringExtra("글쓴이").toString()
            )
        }
*/
    }
}