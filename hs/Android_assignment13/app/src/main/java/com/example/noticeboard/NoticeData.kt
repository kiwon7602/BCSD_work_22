package com.example.noticeboard

import android.net.Uri

data class NoticeData (
    var title: String,
    var content: String,
    var name: String,
    var uri: Uri?
)