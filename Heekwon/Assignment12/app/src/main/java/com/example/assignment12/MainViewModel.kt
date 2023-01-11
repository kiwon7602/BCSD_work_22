package com.example.assignment12

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val postLiveData = MutableLiveData<List<PostData>>()
    private val data = arrayListOf<PostData>()

    fun add(postData: PostData){
        data.add(postData)
        postLiveData.value=data
    }

    fun delete(position : Int){
        data.removeAt(position)
        postLiveData.value=data
    }

    fun modify(postData: PostData, position : Int){
        data[position] = postData
        postLiveData.value=data
    }
}