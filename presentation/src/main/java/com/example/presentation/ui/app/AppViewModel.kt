package com.example.presentation.ui.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.presentation.ui.ChatViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class AppViewModel : ChatViewModel() {
    private val _appContentType = MutableLiveData<AppPageContentType>()
    val appContentType: LiveData<AppPageContentType>
        get() = _appContentType

    fun setAppPageContentType(contentType: AppPageContentType) {
        _appContentType.postValue(contentType)
    }
}