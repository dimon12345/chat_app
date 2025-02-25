package com.example.presentation.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.GetUserIdUseCase
import com.example.domain.profile.GetProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePageViewModel @Inject constructor  (
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfilePageState())
    val uiState = _uiState.asStateFlow()

    init {
        collectProfileData()
    }

    private fun collectProfileData() {
        viewModelScope.launch(Dispatchers.IO) {
            while(true) {
                val userId = getUserIdUseCase().first() ?: 0
                if (userId > 0) {

                    getProfileDataUseCase(userId).collect { profileRequestResult ->
                        val profile = profileRequestResult.profile
                        if (profile.userId != 0) {
                            _uiState.value = _uiState.value.copy(
                                profile = profile,
                                loading = false,
                                profileEditorEnabled = true,
                            )
                        }
                    }
                    break
                }
                delay(1000)
            }
        }
    }
}