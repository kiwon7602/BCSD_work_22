package com.example.assignment10

import android.net.Uri
import android.provider.MediaStore

data class Music(val id:String = "", val title:String = "", val artist:String = "", val albumId:String = "", val duration:Long = 0) {

    fun getMusicUri(): Uri {
        return Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
    }

    fun getAlbumUri(): Uri {
        return Uri.parse("content://media/external/audio/albumart/$albumId")
    }
}