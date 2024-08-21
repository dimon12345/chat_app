package com.example.domain.auth

import com.example.domain.data.AuthDeviceRequestResult

interface AuthRepository {
    suspend fun authDevice(phone: String): AuthDeviceRequestResult
}