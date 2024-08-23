package com.example.domain.auth

import com.example.domain.repository.UserPreferencesRepository
import com.example.domain.repository.UserPreferencesRepository.Companion.REFRESH_TOKEN_KEY
import javax.inject.Inject

class GetRefreshTokenUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke() = userPreferencesRepository.getString(REFRESH_TOKEN_KEY)
}