package com.example.domain.auth

import com.example.domain.data.CheckAuthRequestResult
import com.example.domain.data.SendAuthCodeRequestResult

interface AuthRepository {
    suspend fun sendAuthCode(phone: String): SendAuthCodeRequestResult
    suspend fun checkAuthCode(phone: String, code: String): CheckAuthRequestResult
}