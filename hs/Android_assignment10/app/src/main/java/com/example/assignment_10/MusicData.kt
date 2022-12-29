package com.example.assignment_10

import android.net.Uri
import android.provider.MediaStore

data class MusicData(var id: String ="", var title: String = "",  var artist: String = "", var duration: Long = 0,var albumId: String= "")
{
    fun getMusicUri() : Uri {
        return Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
    }
}
