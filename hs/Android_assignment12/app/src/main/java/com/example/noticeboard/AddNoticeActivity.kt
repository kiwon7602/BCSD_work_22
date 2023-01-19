package com.example.noticeboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noticeboard.databinding.AddNoticeBinding
import com.example.noticeboard.CombineViewModel.viewModel

class AddNoticeActivity : AppCompatActivity() {

    private lateinit var binding: AddNoticeBinding
    private lateinit var notice: NoticeData

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddNoticeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val addBtn = binding.noticeAddBtn
        val returnBtn = binding.returnBtn
        val inputTitle = binding.noticeTitle
        val inputContent = binding.noticeContent
        val inputName = binding.noticeWriter

        addBtn.setOnClickListener{
            notice = NoticeData(inputTitle.text.toString(), inputContent.text.toString(), inputName.text.toString())
            viewModel.addData(notice)
            finish()
        }

        returnBtn.setOnClickListener{
            finish()
        }
    }
}
