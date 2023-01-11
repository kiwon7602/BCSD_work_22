package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.collections.ArrayList

class ViewModel: ViewModel() {
    private val postLists = MutableLiveData<ArrayList<PostItem>>()
    val postList: LiveData<ArrayList<PostItem>> get() = postLists

    private val items = ArrayList<PostItem>()

    init { postLists.value = items }

    fun addPost(postItem: PostItem, position: Int) {
        if(position == -1) {
            items.add(postItem)
            postLists.value = items
        }
        else {
            items[position] = postItem
            postLists.value = items
        }
    }

    fun removePost(position: Int) {
        items.removeAt(position)
        postLists.value = items
    }

    fun getPost(position: Int): PostItem = items[position]
}