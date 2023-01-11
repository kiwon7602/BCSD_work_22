package com.example.myapplication

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: ArrayList<PostItem>) {
        if(recyclerView.adapter == null) recyclerView.adapter = PostAdapter()

        val postAdapter = recyclerView.adapter as PostAdapter
        postAdapter.postItem = items
        postAdapter.notifyDataSetChanged()
    }
}