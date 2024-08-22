package com.example.domain.auth

import com.example.domain.user_preferences.UserPreferencesRepository
import com.example.domain.user_preferences.UserPreferencesRepository.Companion.PHONE_NUMBER_KEY
import javax.inject.Inject

class SavePhoneNumberUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) {
    suspend operator fun invoke(phone: String) = userPreferencesRepository.setString(PHONE_NUMBER_KEY, phone)
}