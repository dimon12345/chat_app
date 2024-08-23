package com.example.domain.auth

import com.example.domain.repository.UserPreferencesRepository
import com.example.domain.repository.UserPreferencesRepository.Companion.ACCESS_TOKEN_KEY
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    operator fun invoke() = userPreferencesRepository.getString(ACCESS_TOKEN_KEY)
}