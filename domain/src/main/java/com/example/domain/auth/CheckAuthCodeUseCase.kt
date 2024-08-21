package com.example.domain.auth

import com.example.domain.data.CheckAuthResult
import kotlinx.coroutines.delay
import javax.inject.Inject

class CheckAuthCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
)
{
    suspend operator fun invoke(
        verificationCode: String,
        phoneNumber: String,
    ): Result<CheckAuthResult> {
        delay(1000)
        return Result.failure(Exception("unimplemented"))
    }
}