package com.example.domain.auth

import com.example.domain.repository.UserPreferencesRepository
import com.example.domain.repository.UserPreferencesRepository.Companion.PHONE_NUMBER_KEY
import javax.inject.Inject

class GetPhoneNumberUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    suspend operator fun invoke() = userPreferencesRepository.getString(PHONE_NUMBER_KEY)
}