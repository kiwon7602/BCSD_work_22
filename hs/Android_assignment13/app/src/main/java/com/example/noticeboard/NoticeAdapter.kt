package com.example.noticeboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noticeboard.databinding.NoticeItemBinding

class NoticeAdapter(): RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {
    var dataSet = mutableListOf<NoticeData>()
    lateinit var onLongClickListener: OnLongClickListener

    class ViewHolder(private val binding: NoticeItemBinding): RecyclerView.ViewHolder(binding.root){
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
            bind(dataSet[position])
            itemView.setOnClickListener {
                itemClickListener.onClick(it, position)
            }
            itemView.setOnLongClickListener {
                onLongClickListener.onItemLongClick(adapterPosition)
                true
            }
        }

    }
    override fun getItemCount(): Int {
        return  dataSet.size
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    interface OnLongClickListener {
        fun onItemLongClick(position: Int)
    }


    inline fun setOnItemLongClickListener(crossinline item: (Int) -> Unit) {
        this.onLongClickListener = object: OnLongClickListener {
            override fun onItemLongClick(position: Int) {
                item(position)
            }
        }
    }

}