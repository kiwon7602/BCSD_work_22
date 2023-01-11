package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.ViewModelSingleton.viewModel
import com.example.myapplication.databinding.ActivityWriteBinding
import java.text.SimpleDateFormat
import java.util.*

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private lateinit var post: PostItem
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        if(intent.extras != null) {
            position = intent.getIntExtra("position", 0)
            post = viewModel.getPost(position)
        }
        else post = PostItem("", "", "", "")

        binding.postItem = post
        binding.writeActivity = this
    }

    fun addPost() {
        val title = binding.titleEditView.text.toString()
        val writer = binding.writerEditView.text.toString()
        val content = binding.editContentView.text.toString()

        val date = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA)
        val time = sdf.format(date).toString()

        post = PostItem(title, writer, content, time)
        viewModel.addPost(post, position)
        finish()
    }
}