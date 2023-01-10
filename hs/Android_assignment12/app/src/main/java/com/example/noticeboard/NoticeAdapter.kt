package com.example.noticeboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noticeboard.databinding.NoticeItemBinding

class NoticeAdapter: RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {
    lateinit var onLongClickListener: OnLongClickListener
    lateinit var onClickListener: OnClickListener
    private val dataSet = mutableListOf<NoticeData>()

    class ViewHolder(private val binding: NoticeItemBinding): RecyclerView.ViewHolder(binding.root){
        val number = binding.numberView
        val data = binding.nameTextView
        fun bind(item: NoticeData){
               binding.data = item
               binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewgroup.context)
        val binding =  NoticeItemBinding.inflate(inflater, viewgroup, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            number.text = (position + 1).toString() + ". "
            bind(dataSet[position])

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
        dataSet.add(NoticeData(name))
        notifyDataSetChanged()
    }

    fun deleteName(position: Int)
    {
        dataSet.removeAt(position)
        notifyDataSetChanged()
    }

    fun reviseData(name: String, position: Int) {
        dataSet[position].name = name
        notifyDataSetChanged()
    }
}