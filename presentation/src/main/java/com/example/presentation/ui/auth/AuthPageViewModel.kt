package com.example.presentation.ui.auth

import androidx.lifecycle.viewModelScope
import com.example.domain.auth.CheckAuthCodeUseCase
import com.example.domain.auth.SendAuthCodeUseCase
import com.example.domain.auth.ValidatePhoneNumberUseCase
import com.example.domain.auth.SavePhoneNumberUseCase
import com.example.domain.auth.StoreCheckAuthCodeResultsUseCase
import com.example.domain.data.CheckAuthResult
import com.example.presentation.ui.app.AppPageContentType
import com.example.presentation.ui.app.AppViewModel
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
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val checkAuthCodeUseCase: CheckAuthCodeUseCase,
    private val storeCheckAuthCodeResultsUseCase: StoreCheckAuthCodeResultsUseCase,
    private val saveUserPhoneUseCase: SavePhoneNumberUseCase,
) : AppViewModel() {
    private var _uiState = MutableStateFlow(AuthPageState())
    val uiState: StateFlow<AuthPageState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        _uiState.value = _uiState.value.copy(
//            phone = "+79219999777",
//            code = "13333",
//            sendCodeButtonEnabled = true,
//            showPinNumberAlert = true,
        )
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        if (!phoneNumber.all(Char::isDigit)) {
            return
        }

        _uiState.value = _uiState.value.copy(
            phone = phoneNumber,
            sendCodeButtonEnabled = validatePhoneNumberUseCase(phoneNumber)
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
            val result = authDeviceUseCase(_uiState.value.phone)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(
                    showPinNumberAlert = true,
                    loading = false,
                )
                saveUserPhoneUseCase(_uiState.value.phone)
            } else {
                _uiState.value = _uiState.value.copy(
                    showPinNumberAlert = false,
                    sendCodeButtonEnabled = true,
                    loading = false,
                )
                sendToast(
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

        val code =
            if (key == "<") {
                if (state.code.isEmpty()) {
                    ""
                } else {
                    state.code
                        .substring(0, state.code.length - 1)
                }
            } else {
                state.code + key
            }

        val inputCompleted = code.length == VERIFICATION_CODE_LENGTH

        _uiState.value = _uiState.value.copy(
            code = code,
            loading = inputCompleted,
        )

        if (inputCompleted) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = checkAuthCodeUseCase(state.phone, code)
                if (result.isSuccess) {
                    val checkAuthResult = result.getOrNull() ?: CheckAuthResult(errorText = "Something wrong")
                    if (checkAuthResult.isUserExists) {
                        storeCheckAuthCodeResultsUseCase(
                            refreshToken = checkAuthResult.refreshToken,
                            accessToken = checkAuthResult.accessToken,
                            userId = checkAuthResult.userId
                        )
                        setAppPageContentType(AppPageContentType.HOME)
                    } else {
                        setAppPageContentType(AppPageContentType.REGISTRATION)
                    }
                } else {
                    sendToast(result.exceptionOrNull()?.message ?: "Something wrong")
                    _uiState.value = _uiState.value.copy(
                        code = "",
                        loading = false,
                    )
                }
            }
        }
    }
}