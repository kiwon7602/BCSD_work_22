package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ViewModelSingleton.viewModel
import com.example.myapplication.databinding.PostItemBinding

class PostAdapter: RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var postItem = mutableListOf<PostItem>()

    class ViewHolder(private val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(postItem: PostItem, position: Int) {
            binding.postItem = postItem

            binding.post.setOnClickListener {
                val intent = Intent(binding.root.context, WriteActivity::class.java)
                intent.putExtra("position", position)
                binding.root.context.startActivity(intent)
            }

            binding.post.setOnLongClickListener {
                viewModel.removePost(position)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    = ViewHolder(PostItemBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(postItem[position], position)

    override fun getItemCount(): Int = postItem.size
}