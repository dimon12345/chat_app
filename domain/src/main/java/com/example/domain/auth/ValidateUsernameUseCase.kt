package com.example.domain.auth

import javax.inject.Inject

class ValidateUsernameUseCase @Inject constructor(
){
    operator fun invoke(username: String): Boolean {
        val regex = "^[A-Za-z0-9_-]*$".toRegex()
        return regex.matches(username)
    }
}