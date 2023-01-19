package com.example.noticeboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private val _noticeLiveData = MutableLiveData<ArrayList<NoticeData>>()
    val noticeDataList: LiveData<ArrayList<NoticeData>> get() = _noticeLiveData

    private val items = ArrayList<NoticeData>()

    init{
        _noticeLiveData.value = items
    }

    fun addData(noticeData: NoticeData)
    {
        items.add(noticeData)
        _noticeLiveData.value = items
    }

    fun removeData(position: Int) {
        items.removeAt(position)
        _noticeLiveData.value = items
    }

    fun getData(position: Int) : NoticeData {
        return items[position]
    }

    fun reviseData(position: Int, noticeData: NoticeData){
        items[position].title = noticeData.title
        items[position].content = noticeData.content
        items[position].name  = noticeData.name
        items[position].uri  = noticeData.uri
        _noticeLiveData.value = items
    }
}