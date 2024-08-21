package com.example.domain.auth

import com.example.domain.data.AuthDeviceResult
import javax.inject.Inject

class SendAuthCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(phoneNumber: String): Result<AuthDeviceResult> {
        return try {
            val result = authRepository.authDevice(phoneNumber)
            when(result.code) {
                201 -> {
                    if (result.success) {
                        Result.success(AuthDeviceResult.SUCCESS)
                    } else {
                        Result.failure(
                            Exception(
                                "Bad server response: ${result.code}"
                            )
                        )
                    }
                }
                else -> {
                    Result.failure(Exception("[${result.code}]: ${result.errorMessage}"))
                }
            }
        } catch(e: Exception) {
            Result.failure(e)
        }
    }
}