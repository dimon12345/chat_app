package com.example.domain.repository

import com.example.domain.data.CheckAuthRequestResult
import com.example.domain.data.ProfileData
import com.example.domain.data.GetProfileDataRequestResult
import com.example.domain.data.RefreshTokenRequestResult
import com.example.domain.data.RegisterRequestResult
import com.example.domain.data.SendAuthCodeRequestResult
import com.example.domain.data.SetProfileDataRequestResult

interface MainRepository {
    suspend fun sendAuthCode(phone: String): SendAuthCodeRequestResult
    suspend fun checkAuthCode(phone: String, code: String): CheckAuthRequestResult
    suspend fun register(phone: String, name: String, username: String): RegisterRequestResult
    suspend fun refreshToken(refreshToken: String, accessToken: String): RefreshTokenRequestResult
    suspend fun getProfile(token: String): GetProfileDataRequestResult
    suspend fun updateProfile(token: String, profile: ProfileData): SetProfileDataRequestResult
}