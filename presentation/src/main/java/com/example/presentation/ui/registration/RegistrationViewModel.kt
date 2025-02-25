package com.example.presentation.ui.registration

import androidx.lifecycle.viewModelScope
import com.example.domain.auth.StoreCheckAuthCodeResultsUseCase
import com.example.domain.auth.RegisterUseCase
import com.example.domain.auth.ValidateUsernameUseCase
import com.example.presentation.ui.app.AppPageContentType
import com.example.presentation.ui.app.AppPageStateSelector
import com.example.presentation.ui.app.AppViewModel
import com.example.presentation.ui.auth.ToastNotificator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateUsernameUseCase: ValidateUsernameUseCase,
    private val storeCheckAuthCodeResultsUseCase: StoreCheckAuthCodeResultsUseCase,
    private val registerUseCase: RegisterUseCase,
) : AppViewModel() {
    private val _uiState = MutableStateFlow(RegistrationPageState())
    val uiState = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(
            name = name,
        )
    }

    fun onUsernameChanged(username: String) {
        if (username.isEmpty()) {
            return
        }

        if (!validateUsernameUseCase(username)) {
            return
        }

        _uiState.value = _uiState.value.copy(
            username = username,
            submitButtonEnabled = true,
        )
    }

    fun onSubmit() {
        _uiState.value = _uiState.value.copy(
            loading = true,
            submitButtonEnabled = false,
        )

        viewModelScope.launch(Dispatchers.IO) {
            val state = _uiState.value
            val result = registerUseCase(state.phone, state.name, state.username)
            if (result.success) {
                storeCheckAuthCodeResultsUseCase(
                    refreshToken = result.refreshToken,
                    accessToken = result.accessToken,
                    userId = result.userId,
                )
                setAppPageContentType(AppPageContentType.HOME)
            } else {
                sendToast(result.errorMessage)
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    submitButtonEnabled = true,
                )
            }
        }
    }
}