package com.example.domain.auth

import com.example.domain.data.CheckAuthResult
import kotlinx.coroutines.delay
import javax.inject.Inject

class CheckAuthCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
)
{
    suspend operator fun invoke(
        phone: String,
        code: String,
    ): Result<CheckAuthResult> {
        return try {
            val result = authRepository.checkAuthCode(phone, code)
            return when(result.code) {
                200 -> {
                    with(result) {
                        Result.success(
                            CheckAuthResult(
                                success = success,
                                refreshToken = refreshToken,
                                accessToken = accessToken,
                                userId = userId,
                                isUserExists = isUserExists,
                            )
                        )
                    }
                }
                else -> {
                    Result.failure(Exception("${result.code}: ${result.errorMessage}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}