package com.example.assignment12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment12.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerViewAdapter(private var postList : List<PostData>, itemListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.PostViewHolder>() {

    private val listener: OnItemClickListener = itemListener

    class PostViewHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return PostViewHolder(ItemRecyclerBinding.bind(view))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val time = postList[position].time
        holder.binding.User.text = postList[position].user
        holder.binding.Title.text = postList[position].title
        holder.binding.Content.text = postList[position].content
        holder.binding.Time.text = SimpleDateFormat("hh:mm").format(time)

        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemClickListener.onItemLongClick(it, position)
            return@setOnLongClickListener true

        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    fun setData(postData : List<PostData>){
        postList = postData
        notifyDataSetChanged()
    }

}