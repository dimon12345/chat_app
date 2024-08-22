package com.example.domain.auth

import com.example.domain.data.RegisterRequestResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke (phone: String, name: String, username: String): RegisterRequestResult =
        authRepository.register(phone, name, username)
}