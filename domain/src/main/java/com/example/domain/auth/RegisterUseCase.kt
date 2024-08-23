package com.example.domain.auth

import com.example.domain.data.RegisterRequestResult
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val mainRepository: MainRepository
){
    suspend operator fun invoke (phone: String, name: String, username: String): RegisterRequestResult =
        mainRepository.register(phone, name, username)
}