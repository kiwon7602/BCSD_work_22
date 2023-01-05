package com.example.assignment_10

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_10.databinding.ItemMusicBinding
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.concurrent.TimeUnit

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    var dataList = mutableListOf<MusicData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            val item = dataList[position]
            titleTextView.text = item.title
            artistTextView.text = item.artist
            durationTextView.text = getDuration(item.duration)
            val albumArt = getAlbumArt(itemView.context, itemView.resources, item.albumId.toUri())
            albumArtImage.setImageDrawable(albumArt)
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
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


    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        val artistTextView: TextView = itemView.findViewById(R.id.artist_text_view)
        val durationTextView: TextView = itemView.findViewById(R.id.duration_text_view)
        val albumArtImage: ImageView = itemView.findViewById(R.id.album_art_image)

    }

    private fun getAlbumArt(context: Context, resources: Resources, albumUri: Uri): Drawable? {
        var inputStream: InputStream? = null // 외부 데이터들이 크기가 커 스트림 사용
        val albumArt: Drawable? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                BitmapDrawable(
                    resources,
                    //contentResolver를 통해 이미지를 가져온다
                    context.contentResolver.loadThumbnail(albumUri, Size(500, 500), null)
                    //Resolver의 형태는 미디어 스토어에서 받은거 그대로 넘겨줌
                )
            } catch (e: FileNotFoundException) {
                ResourcesCompat.getDrawable(resources, R.drawable.ic_no_album_art, null) // 이미지가 없으면 앱이 종료되지 않도록 설정
            }
        } else {
            try {
                inputStream = context.contentResolver.openInputStream(albumUri)
                val option = BitmapFactory.Options()
                option.outWidth = 500
                option.outHeight = 500
                option.inSampleSize = 2
                BitmapDrawable(resources, BitmapFactory.decodeStream(inputStream, null, option))
            } catch (e: FileNotFoundException) {
                ResourcesCompat.getDrawable(resources, R.drawable.ic_no_album_art, null)
            }
        }
        inputStream?.close()

        return albumArt
    }

    private fun getDuration(milliseconds: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes =
            TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(hours)
        val seconds =
            TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(minutes)

        val duration = if (hours.toInt() != 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
        return duration
    }

    fun String.toUri(): Uri {
        return Uri.parse(this)
    }
}
