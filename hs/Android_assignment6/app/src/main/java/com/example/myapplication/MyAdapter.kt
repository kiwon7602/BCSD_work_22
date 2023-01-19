package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>()
{
    lateinit var onLongClickListener: OnLongClickListener
    lateinit var onClickListener: OnClickListener
    private val dataSet = mutableListOf<String>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val number: TextView
        val data: TextView

        init {
            number = view.findViewById(R.id.numberView)
            data = view.findViewById(R.id.name_text_view)
        }
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewgroup.context)
            .inflate(R.layout.my_recyclerview, viewgroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            number.text = (position + 1).toString() + ". "
            data.text = dataSet[position]

            data.setOnClickListener {
                onClickListener.onItemClick(adapterPosition)
            }

            data.setOnLongClickListener {
                onLongClickListener.onItemLongClick(adapterPosition)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return  dataSet.size
    }

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    interface OnLongClickListener {
        fun onItemLongClick(position: Int)
    }

    inline fun setOnItemClickListener(crossinline item: (Int) -> Unit) {
        this.onClickListener = object: OnClickListener {
            override fun onItemClick(position: Int) {
                item(position)
            }
        }
    }

    inline fun setOnItemLongClickListener(crossinline item: (Int) -> Unit) {
        this.onLongClickListener = object: OnLongClickListener {
            override fun onItemLongClick(position: Int) {
                item(position)
            }
        }
    }

    fun addName(name: String)
    {
        dataSet.add(name)
        notifyDataSetChanged()
    }

    fun deleteName(position: Int)
    {
        dataSet.removeAt(position)
        notifyDataSetChanged()
    }

    fun reviseData(name: String, position: Int) {
        dataSet[position] = name
        notifyDataSetChanged()
    }
}
