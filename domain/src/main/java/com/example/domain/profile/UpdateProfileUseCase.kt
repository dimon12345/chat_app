package com.example.domain.profile

import com.example.domain.auth.GetAccessTokenUseCase
import com.example.domain.auth.GetRefreshTokenUseCase
import com.example.domain.auth.RefreshTokenUseCase
import com.example.domain.data.ProfileData
import com.example.domain.data.UpdateProfileResult
import com.example.domain.repository.LocalRepository
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val localRepository: LocalRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
) {
    suspend operator fun invoke(profile: ProfileData): UpdateProfileResult {
        try {
            while (true) {
                val token = getAccessTokenUseCase().first()
                if (token.isNullOrBlank()) {
                    return UpdateProfileResult(success = false, errorText = "Something wrong")
                }

                val response = mainRepository.updateProfile(token, profile)
                when (response.code) {
                    200 -> {
                        localRepository.updateProfileData(profile)
                        return UpdateProfileResult(success = true)
                    }

                    401 -> {
                        val refreshToken = getRefreshTokenUseCase().first() ?: ""
                        assert(refreshToken.isNotEmpty())
                        refreshTokenUseCase(
                            refreshToken = refreshToken,
                            accessToken = token)
                        continue
                    }

                    else -> {
                        return UpdateProfileResult(
                            success = false,
                            errorText = response.errorMessage,
                        )
                    }
                }
            }
        } catch (e: Exception) {
            return UpdateProfileResult(
                success = false,
                errorText = e.message ?: "Something wrong"
            )
        }
    }
}