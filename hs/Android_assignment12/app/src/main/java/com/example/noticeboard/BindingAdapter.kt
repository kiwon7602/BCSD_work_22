package com.example.noticeboard

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: ArrayList<NoticeData>) {
        if(recyclerView.adapter == null) recyclerView.adapter = NoticeAdapter()

        val noticeAdapter = recyclerView.adapter as NoticeAdapter
        noticeAdapter.dataSet = items
        noticeAdapter.notifyDataSetChanged()
    }
}