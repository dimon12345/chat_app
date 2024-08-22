package com.example.domain.auth

import javax.inject.Inject

class ValidateUsernameUseCase @Inject constructor(
){
    operator fun invoke(phoneNumber: String): Boolean {
        return true
    }
}