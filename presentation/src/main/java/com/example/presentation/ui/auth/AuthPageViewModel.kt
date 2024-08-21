package com.example.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.CheckAuthCodeUseCase
import com.example.domain.auth.SendAuthCodeUseCase
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
    private val authDeviceUseCase: SendAuthCodeUseCase,
    private val checkPhoneNumberUseCase: CheckPhoneNumberUseCase,
    private val checkAuthCodeUseCase: CheckAuthCodeUseCase,
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
            showPinNumberAlert = false,
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

    fun onVerificationCodeKey(key: String) {
        val state = _uiState.value
        if (state.loading) {
            return
        }

        val verificationCode = if (key == "<") {
            if (state.verificationCode.isEmpty()) {
                ""
            } else {
                state.verificationCode
                    .substring(0, state.verificationCode.length - 1)
            }
        } else {
            state.verificationCode + key
        }

        val loading = verificationCode.length == VERIFICATION_CODE_LENGTH

        _uiState.value = _uiState.value.copy(
            verificationCode = verificationCode,
            loading = loading,
        )

        if (loading) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = checkAuthCodeUseCase(verificationCode, state.phoneNumber)
                if (!result.isSuccess) {
                    toastNotificator?.sendToast(result.exceptionOrNull()?.message ?: "Something wrong")
                    _uiState.value = _uiState.value.copy(
                        verificationCode = "",
                        loading = false,
                    )
                }
            }
        }
    }
}