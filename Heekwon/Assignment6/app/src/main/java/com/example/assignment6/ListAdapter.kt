package com.example.assignment6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(val itemList: ArrayList<ListItem>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    lateinit var onItemClickListener: OnItemClickListener
    lateinit var onItemLongClickListener: OnItemLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.name.text = itemList[position].name
        holder.name.setOnClickListener{
            onItemClickListener.onItemClick(position)
        }
        holder.name.setOnLongClickListener{
            onItemLongClickListener.onItemLongClick(position)
            return@setOnLongClickListener true
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
    }

    inline fun setOnItemClickListener(crossinline onItemClick: (Int) -> Unit) {
        onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                onItemClick(position)
            }
        }
    }

    inline fun setOnItemLongClickListener(crossinline onItemLongClick: (Int) -> Unit) {
        onItemLongClickListener = object : OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                onItemLongClick(position)
            }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name)
    }
}