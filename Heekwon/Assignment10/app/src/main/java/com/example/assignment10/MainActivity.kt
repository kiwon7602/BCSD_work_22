package com.example.assignment10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startProcess()
        } else {
            Toast.makeText(this, "권한 요청을 승인해야지만 앱을 실행할 수 있습니다.", Toast.LENGTH_LONG).show()
        }
    }

    private val permissions = Manifest.permission.READ_EXTERNAL_STORAGE
    private lateinit var serviceIntent : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissionLauncher.launch(permissions)


    }

    fun startProcess(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        val adapter = MusicAdapter()
        adapter.musicList.addAll(getMusicList())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)
        val playAlbum : ImageView = findViewById(R.id.playingAlbum)
        val playArtist : TextView = findViewById(R.id.playingArtist)
        val playTitle : TextView = findViewById(R.id.playingTitle)
        val stopButton : ImageButton = findViewById(R.id.imageButton2)
        adapter.setItemClickListener(object : MusicAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val MusicInfo = getMusicList()[position]
                serviceStart(MusicInfo.id, MusicInfo.title, MusicInfo.artist)
                playAlbum.setImageURI(Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,MusicInfo.albumId))
                playArtist.text = MusicInfo.artist
                playTitle.text = MusicInfo.title
            }
        })
        stopButton.setOnClickListener{
            serviceStop()
        }

    }

    fun serviceStart(id : String, title : String, artist : String)
    {
        serviceIntent = Intent(this, MusicService::class.java)
        serviceIntent.putExtra("Id", id)
        serviceIntent.putExtra("Title", title)
        serviceIntent.putExtra("Artist", artist)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        }
        else{
            startService(serviceIntent)
        }
    }

    fun serviceStop()
    {
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
    }
    fun getMusicList(): List<Music>{
        val musicListUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION
        )
        val cursor = contentResolver.query(musicListUri, proj, null, null, null)
        val musicList = mutableListOf<Music>()
        while(cursor?.moveToNext() ?: false){
            val id = cursor!!.getString(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val albumId = cursor.getString(3)
            val duration = cursor.getLong(4)

            val music = Music(id, title, artist, albumId, duration)
            musicList.add(music)
        }
        return musicList
    }
}