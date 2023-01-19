package com.example.noticeboard

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object BindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: ArrayList<NoticeData>) {
        if(recyclerView.adapter == null) recyclerView.adapter = NoticeAdapter()

        val noticeAdapter = recyclerView.adapter as NoticeAdapter
        noticeAdapter.dataSet = items
        noticeAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("image")
    @JvmStatic
    fun setImage(imageView: ImageView, imageUrl: Any){
        Glide.with(imageView.context)
            .load(imageUrl)
            .override(5000,1000)
            .into(imageView)
    }
}