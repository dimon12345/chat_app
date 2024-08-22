package com.example.presentation.ui.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.GetAccessTokenUseCase
import com.example.domain.auth.GetRefreshTokenUseCase
import com.example.domain.auth.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppState())
    val uiState = _uiState.asStateFlow()
    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(
                currentAppStateType = AppContentType.REGISTRATION
            )

//            val userId = getUserIdUseCase().first() ?: 0
//            val refreshToken = getRefreshTokenUseCase().first() ?: ""
//            val appContentType = if ( userId > 0L) {
//                assert(refreshToken.isNotEmpty())
//                assert(!getAccessTokenUseCase().first().isNullOrBlank())
//                AppContentType.HOME
//            } else {
//                AppContentType.AUTHORIZATION
//            }
//            _uiState.value = _uiState.value.copy(
//                currentAppStateType = appContentType
//            )
        }
    }

    fun selectState(appStateType: AppContentType) {
        _uiState.value = _uiState.value.copy(
            currentAppStateType = appStateType,
        )
    }
}