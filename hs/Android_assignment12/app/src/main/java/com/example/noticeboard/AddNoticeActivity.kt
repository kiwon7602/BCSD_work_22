package com.example.noticeboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.noticeboard.databinding.ActivityMainBinding
import com.example.noticeboard.databinding.AddNoticeBinding

class AddNoticeActivity : AppCompatActivity() {

    private lateinit var binding: AddNoticeBinding
    private lateinit var viewModel : MainViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddNoticeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myAdapter = NoticeAdapter()
        //val addBtn = binding.noticeAddBtn
        //val returnBtn = binding.returnBtn
        val inputTitle = binding.noticeTitle
        val inputContent = binding.noticeContent
        val inputName = binding.noticeWriter

        /*addBtn.setOnClickListener{

            viewModel.add_data(NoticeData(inputTitle.text.toString(), inputContent.text.toString(), inputName.text.toString()))
            val intent_main = Intent(this, MainActivity::class.java)
            //intent_main.putExtra("제목",inputTitle.text.toString())
            //intent_main.putExtra("내용",inputContent.text.toString())
            //intent_main.putExtra("글쓴이",inputName.text.toString())
            startActivity(intent_main)
        }

        returnBtn.setOnClickListener{
            val intent_main = Intent(this, MainActivity::class.java)
            startActivity(intent_main)
        }
            */
    }
}
