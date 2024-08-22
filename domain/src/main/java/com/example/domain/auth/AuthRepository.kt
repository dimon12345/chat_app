package com.example.domain.auth

import com.example.domain.data.CheckAuthRequestResult
import com.example.domain.data.RegisterRequestResult
import com.example.domain.data.SendAuthCodeRequestResult

interface AuthRepository {
    suspend fun sendAuthCode(phone: String): SendAuthCodeRequestResult
    suspend fun checkAuthCode(phone: String, code: String): CheckAuthRequestResult
    suspend fun register(phone: String, name: String, username: String): RegisterRequestResult
}