package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.TimeUnit

class TimeAdapter: RecyclerView.Adapter<TimeAdapter.ViewHolder>() {
    private var lapTimeList = mutableListOf<TimeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.time_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            lapNumberTextView.text = lapTimeList[position].lapNumber.toString()
            lapTimeTextView.text = lapTimeList[position].lapTime
        }
    }

    override fun getItemCount(): Int = lapTimeList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val lapNumberTextView: TextView = itemView.findViewById(R.id.lap_number_text_view)
        val lapTimeTextView: TextView = itemView.findViewById(R.id.lap_time_text_view)
    }

    fun setTimeList(lapTimeList: MutableList<TimeData>) {
        this.lapTimeList = lapTimeList
    }
}