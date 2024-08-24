package com.example.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class ChatViewModel : ViewModel() {
    private val _toaster = MutableLiveData<String>()
    val toaster: LiveData<String>
        get() = _toaster

    fun sendToast(text: String) {
        _toaster.postValue(text)
    }
}