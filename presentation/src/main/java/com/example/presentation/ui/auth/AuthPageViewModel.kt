package com.example.presentation.ui.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthPageViewModel @Inject constructor(
) : ViewModel() {
    private var _uiState = MutableStateFlow(AuthPageState())
    val uiState: StateFlow<AuthPageState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        _uiState.value = _uiState.value.copy(
            phoneNumber = "+79219999999",
        )
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(
            phoneNumber = phoneNumber,
        )
    }

    fun onRegistrationSubmit() {
        Timber.d("on send code clicked")
    }
}