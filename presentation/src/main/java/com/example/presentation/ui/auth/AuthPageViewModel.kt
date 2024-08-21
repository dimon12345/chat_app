package com.example.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.AuthDeviceUseCase
import com.example.domain.auth.CheckPhoneNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthPageViewModel @Inject constructor(
    private val authDeviceUseCase: AuthDeviceUseCase,
    private val checkPhoneNumberUseCase: CheckPhoneNumberUseCase,
) : ViewModel() {
    private var _uiState = MutableStateFlow(AuthPageState())
    val uiState: StateFlow<AuthPageState> = _uiState.asStateFlow()

    var toastNotificator: ToastNotificator? = null

    init {
        loadSettings()
    }

    private fun loadSettings() {
        _uiState.value = _uiState.value.copy(
            phoneNumber = "+79219999999",
            sendCodeButtonEnabled = true,
        )
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(
            phoneNumber = phoneNumber,
            sendCodeButtonEnabled = checkPhoneNumberUseCase(phoneNumber)
        )
    }

    fun onAuthSubmit() {
        _uiState.value = _uiState.value.copy(
            sendCodeButtonEnabled = false,
            loading = true,
        )

        viewModelScope.launch(
            Dispatchers.IO,
        ) {
            val result = authDeviceUseCase(_uiState.value.phoneNumber)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(
                    showPinNumberAlert = true,
                    loading = false,
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    showPinNumberAlert = false,
                    sendCodeButtonEnabled = true,
                    loading = false,
                )
                toastNotificator?.sendToast(
                    result.exceptionOrNull()?.message ?: "Something wrong"
                )
            }
        }
    }
}