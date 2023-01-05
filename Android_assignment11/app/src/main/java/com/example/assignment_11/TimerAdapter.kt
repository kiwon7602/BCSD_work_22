package com.example.assignment_11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TimerAdapter: RecyclerView.Adapter<TimerAdapter.ViewHolder>() {
    private var timeList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_time, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            lapTimeTextView.text = timeList[position].toString()
        }
    }

    override fun getItemCount(): Int = timeList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val lapTimeTextView: TextView = itemView.findViewById(R.id.time_item)
    }

    fun setTimeList(timeList: MutableList<String>) {
        this.timeList = timeList
    }

}