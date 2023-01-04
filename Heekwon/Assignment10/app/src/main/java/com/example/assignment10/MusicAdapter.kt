package com.example.assignment10

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment10.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.Holder>() {
    val musicList = mutableListOf<Music>()
    var mediaPlayer:MediaPlayer? = null
    private val TAG = "MusicAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(view)

    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music = musicList[position]
        holder.setMusic(music)
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener : OnItemClickListener

    inner class Holder(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        var musicUri: Uri? = null

        init{
            itemView.setOnClickListener {
                if(mediaPlayer != null){
                    mediaPlayer?.release()
                    mediaPlayer = null
                }

                mediaPlayer = MediaPlayer.create(itemView.context, musicUri)
                mediaPlayer?.start()
            }
        }

        fun setMusic(music:Music){
            musicUri = music.getMusicUri()
            binding.imageAlbum.setImageURI(music.getAlbumUri())
            binding.textArtist.text = music.artist
            binding.textTitle.text = music.title
            val sdf = SimpleDateFormat("HH:mm:ss")
            binding.textDuration.text = sdf.format(music.duration)

        }
    }
}