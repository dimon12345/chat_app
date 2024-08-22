package com.example.domain.auth

import javax.inject.Inject

class CheckPhoneValidUseCase @Inject constructor(
) {
    operator fun invoke(phoneNumber: String): Boolean {
        val numberRegex = "^[+]?[0-9]{11,11}$".toRegex()
        return phoneNumber.matches(numberRegex)
    }
}