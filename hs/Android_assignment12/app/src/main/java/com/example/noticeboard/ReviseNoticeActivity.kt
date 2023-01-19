package com.example.noticeboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noticeboard.databinding.AddNoticeBinding
import com.example.noticeboard.databinding.ReviseNoticeBinding

class ReviseNoticeActivity: AppCompatActivity() {

    private lateinit var binding: ReviseNoticeBinding
    private lateinit var notice: NoticeData

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReviseNoticeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val addBtn = binding.noticeReviseBtn
        val returnBtn = binding.returnBtn
        val inputTitle = binding.reNoticeTitle
        val inputContent = binding.reNoticeContent
        val inputName = binding.reNoticeWriter
        val num = intent.getIntExtra("위치", 0)

        notice =  CombineViewModel.viewModel.getData(num)
        binding.data = notice

        addBtn.setOnClickListener{
            notice = NoticeData(inputTitle.text.toString(), inputContent.text.toString(), inputName.text.toString())
            CombineViewModel.viewModel.reviseData(num, notice)
            finish()
        }

        returnBtn.setOnClickListener{
            finish()
        }
    }
}