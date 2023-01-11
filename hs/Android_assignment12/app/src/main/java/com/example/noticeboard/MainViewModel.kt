package com.example.noticeboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private val _noticeLiveData = MutableLiveData<MutableList<NoticeData>>()
    val noticedata = mutableListOf<NoticeData>()

    val currentValue: LiveData<MutableList<NoticeData>>
        get() = _noticeLiveData

    fun add_data(noticeData: NoticeData)
    {
        noticedata.add(noticeData)
        _noticeLiveData.value = noticedata
    }
    fun get_size() : Int {
        return noticedata.size
    }
}