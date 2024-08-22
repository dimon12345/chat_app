package com.example.presentation.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.GetPhoneNumberUseCase
import com.example.domain.auth.StoreCheckAuthCodeResultsUseCase
import com.example.domain.auth.RegisterUseCase
import com.example.domain.auth.ValidateUsernameUseCase
import com.example.presentation.ui.app.AppContentType
import com.example.presentation.ui.app.AppStateSelector
import com.example.presentation.ui.auth.ToastNotificator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val getPhoneNumberUseCase: GetPhoneNumberUseCase,
    private val validateUsernameUseCase: ValidateUsernameUseCase,
    private val storeCheckAuthCodeResultsUseCase: StoreCheckAuthCodeResultsUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationPageState())
    val uiState = _uiState.asStateFlow()

    var toastNotificator: ToastNotificator? = null
    var appStateSelector: AppStateSelector? = null

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch (Dispatchers.IO) {
            val phone = getPhoneNumberUseCase().first() ?: ""
            assert(phone.isNotEmpty())
            _uiState.value = _uiState.value.copy(
                phone = phone,
            )
        }
    }

    fun onNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(
            name = name,
        )
    }

    fun onUsernameChanged(username: String) {
        _uiState.value = _uiState.value.copy(
            username = username,
        )

        viewModelScope.launch(Dispatchers.IO) {
            if (validateUsernameUseCase(username)) {
                _uiState.value = _uiState.value.copy(
                    submitButtonEnabled = true,
                )
            }
        }
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
                appStateSelector?.selectState(AppContentType.HOME)
            } else {
                toastNotificator?.sendToast(result.errorMessage)
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    submitButtonEnabled = true,
                )
            }
        }
    }
}