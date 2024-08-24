package com.example.domain.auth

import com.example.domain.repository.MainRepository
import com.example.domain.repository.UserPreferencesRepository
import com.example.domain.repository.UserPreferencesRepository.Companion.ACCESS_TOKEN_KEY
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    suspend operator fun invoke(
        refreshToken: String,
        accessToken: String,
    ) {
        val result = mainRepository.refreshToken(refreshToken, accessToken)
        if (result.code == 200) {
            userPreferencesRepository.setString(ACCESS_TOKEN_KEY, result.accessToken)
        } else {
            delay(10_000)
        }
    }
}