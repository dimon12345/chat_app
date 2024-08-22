package com.example.domain.auth

import com.example.domain.user_preferences.UserPreferencesRepository
import com.example.domain.user_preferences.UserPreferencesRepository.Companion.ACCESS_TOKEN_KEY
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke() = userPreferencesRepository.getString(ACCESS_TOKEN_KEY)
}