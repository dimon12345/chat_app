package com.example.presentation.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomePageState())
    val uiState = _uiState.asStateFlow()

    fun onProfile() {
        _uiState.value = _uiState.value.copy(
            contentType = HomePageContentType.PROFILE,
        )
    }

    fun onProfileBackPressed() {
        _uiState.value = _uiState.value.copy(
            contentType = HomePageContentType.CHAT_LIST,
        )
    }

    fun onProfileEditPressed() {
        _uiState.value = _uiState.value.copy(
            contentType = HomePageContentType.PROFILE_EDITOR,
        )
    }

    fun onProfileEditorBackPressed() {
        _uiState.value = _uiState.value.copy(
            contentType = HomePageContentType.PROFILE,
        )
    }
}