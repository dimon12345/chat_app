package com.example.domain.auth

import com.example.domain.user_preferences.UserPreferencesRepository
import com.example.domain.user_preferences.UserPreferencesRepository.Companion.USER_ID_KEY
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke() = userPreferencesRepository.getLong(USER_ID_KEY)
}