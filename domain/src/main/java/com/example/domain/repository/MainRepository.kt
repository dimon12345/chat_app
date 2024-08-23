package com.example.domain.repository

import com.example.domain.data.CheckAuthRequestResult
import com.example.domain.data.ProfileDataRequestResult
import com.example.domain.data.RefreshTokenRequestResult
import com.example.domain.data.RegisterRequestResult
import com.example.domain.data.SendAuthCodeRequestResult

interface MainRepository {
    suspend fun sendAuthCode(phone: String): SendAuthCodeRequestResult
    suspend fun checkAuthCode(phone: String, code: String): CheckAuthRequestResult
    suspend fun register(phone: String, name: String, username: String): RegisterRequestResult
    suspend fun refreshToken(refreshToken: String): RefreshTokenRequestResult
    suspend fun getProfile(token: String): ProfileDataRequestResult
}