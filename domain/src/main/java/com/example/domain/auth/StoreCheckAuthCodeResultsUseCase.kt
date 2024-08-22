package com.example.domain.auth

import com.example.domain.user_preferences.UserPreferencesRepository
import com.example.domain.user_preferences.UserPreferencesRepository.Companion.ACCESS_TOKEN_KEY
import com.example.domain.user_preferences.UserPreferencesRepository.Companion.REFRESH_TOKEN_KEY
import com.example.domain.user_preferences.UserPreferencesRepository.Companion.USER_ID_KEY
import javax.inject.Inject

class StoreCheckAuthCodeResultsUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    suspend operator fun invoke(
        refreshToken: String,
        accessToken: String,
        userId: Long,
    ) {
        userPreferencesRepository.setString(REFRESH_TOKEN_KEY, refreshToken)
        userPreferencesRepository.setString(ACCESS_TOKEN_KEY, accessToken)
        userPreferencesRepository.setLong(USER_ID_KEY, userId)
    }
}