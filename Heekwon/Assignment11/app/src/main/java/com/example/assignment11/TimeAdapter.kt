package com.example.assignment11

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class TimerAdapter : RecyclerView.Adapter<TimerAdapter.ViewHolder>(){
    private val timeList = mutableListOf<TimeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.time_layout, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            val time = timeList[position].time
            val min = time / 1000 % 3600 / 60
            val sec = time / 1000 % 60
            val msec = time % 1000 / 10
            TimeTextView.text = String.format("%02d : %02d : %02d", min, sec, msec)
        }
    }
    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val TimeTextView : TextView = itemView.findViewById(R.id.time_item)
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

    fun addTimeData(lapTime: TimeData) {
        timeList.add(lapTime)
        notifyItemInserted(timeList.size)
    }
}