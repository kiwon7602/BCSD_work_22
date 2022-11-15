package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter: RecyclerView.Adapter<NameAdapter.ViewHolder>() {
    private val nameList = mutableListOf<String>()

    lateinit var onClickListener: OnClickListener
    lateinit var onLongClickListener: OnLongClickListener

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.name_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            nameText.text = nameList[position]

            nameText.setOnClickListener {
                onClickListener.onItemClick(adapterPosition)
            }

            nameText.setOnLongClickListener {
                onLongClickListener.onItemLongClick(adapterPosition)
                true
            }
        }
    }

    override fun getItemCount(): Int = nameList.size

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

    fun addData(name: String) {
        nameList.add(name)
        notifyDataSetChanged()
    }

    fun removeData(position: Int) {
        nameList.removeAt(position)
        notifyDataSetChanged()
    }

    fun changeData(name: String, position: Int) {
        nameList[position] = name
        notifyDataSetChanged()
    }
}