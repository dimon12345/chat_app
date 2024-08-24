package com.example.domain.profile

import com.example.domain.auth.GetAccessTokenUseCase
import com.example.domain.auth.GetRefreshTokenUseCase
import com.example.domain.auth.RefreshTokenUseCase
import com.example.domain.data.GetProfileDataRequestResult
import com.example.domain.repository.LocalRepository
import com.example.domain.repository.MainRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val mainRepository: MainRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
) {

    suspend operator fun invoke(userId: Int) = localRepository.getProfileData(userId).map { profile ->
        if (profile.userId > 0) {
            return@map GetProfileDataRequestResult(
                success = true,
                profile = profile,
            )
        } else {
            while(true) {
                val token = getAccessTokenUseCase().first() ?: ""
                assert(token.isNotEmpty())

                val profileDataResult = mainRepository.getProfile(token)
                when (profileDataResult.code) {
                    200 -> {
                        assert(profileDataResult.profile.userId == userId)
                        localRepository.updateProfileData(profileDataResult.profile)
                        return@map GetProfileDataRequestResult(
                            success = true,
                            profile = profileDataResult.profile
                        )
                   }
                    401 -> {
                        val refreshToken = getRefreshTokenUseCase().first() ?: ""
                        assert(refreshToken.isNotEmpty())
                        refreshTokenUseCase(
                            refreshToken = refreshToken,
                            accessToken = token
                        )
                        continue
                    }
                    else -> break
                }
            }

            GetProfileDataRequestResult(
                success = false,
            )
        }
    }
}