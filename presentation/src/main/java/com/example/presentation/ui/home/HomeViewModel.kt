package com.example.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.presentation.ui.ChatViewModel

abstract class HomeViewModel: ChatViewModel() {
    private val _content = MutableLiveData<HomePageContentType>()
    val content: LiveData<HomePageContentType>
        get() = _content

    fun setHomePageContentType(contentType: HomePageContentType) {
        _content.postValue(contentType)
    }
}