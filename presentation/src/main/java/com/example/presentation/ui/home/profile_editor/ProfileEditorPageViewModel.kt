package com.example.presentation.ui.home.profile_editor

import androidx.lifecycle.viewModelScope
import com.example.domain.auth.GetUserIdUseCase
import com.example.domain.profile.GetProfileDataUseCase
import com.example.domain.profile.UpdateProfileUseCase
import com.example.presentation.ui.home.HomePageContentType
import com.example.presentation.ui.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditorPageViewModel @Inject constructor(
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
) : HomeViewModel() {
    private val _uiState = MutableStateFlow(ProfileEditorPageState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            while(true) {
                val userId = getUserIdUseCase().first() ?: 0
                if (userId > 0) {
                    val profile = getProfileDataUseCase(userId).first().profile
                    assert(profile.userId > 0)
                    _uiState.value = _uiState.value.copy(
                        profile = profile,
                        loading = false,
                    )
                    break
                }
                delay(1000)
            }
        }
    }

    fun onNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                name = name,
            ),
            applyButtonEnabled = true,
        )
    }

    fun onCityChanged(city: String) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                city = city,
            ),
            applyButtonEnabled = true,
        )
    }

    fun onStatusChanged(status: String) {
        _uiState.value = _uiState.value.copy(
            profile = _uiState.value.profile.copy(
                status = status,
            ),
            applyButtonEnabled = true,
        )
    }

    fun onApplyClick() {
        _uiState.value = _uiState.value.copy(
            loading = true,
        )

        viewModelScope.launch(Dispatchers.IO) {
            val result = updateProfileUseCase(_uiState.value.profile)
            if (result.success) {
                setHomePageContentType(HomePageContentType.PROFILE)
                _uiState.value = _uiState.value.copy(
                    applyButtonEnabled = false,
                    loading = false,
                )
            } else {
                sendToast(result.errorText)
            }
        }
    }
}