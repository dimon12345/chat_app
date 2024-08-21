package com.example.data.repository

import com.example.data.api.MangoTestBackendApiService
import com.example.data.entity.AuthDeviceResultEntity
import com.example.domain.auth.AuthRepository
import com.example.domain.data.AuthDeviceRequestResult
import javax.inject.Inject

class MangoTestBackendAuthRepository @Inject constructor(
    private val mangoTestBackendApiService: MangoTestBackendApiService,
) : AuthRepository {
    override suspend fun authDevice(
        phone: String
    ): AuthDeviceRequestResult {
        return try {
            val args = HashMap<String, String>()
            args["phone"] = phone
            val response = mangoTestBackendApiService.authDevice(args)
            val resultEntity: AuthDeviceResultEntity = response.body() ?: AuthDeviceResultEntity()
            val serverError = response.errorBody()?.string() ?: ""
            resultEntity.toAuthDeviceRequestResult(
                serverCode = response.code(),
                serverError = serverError,
            )
        } catch (e: Exception) {
            assert(e.message?.isNotEmpty() == true)
            AuthDeviceRequestResult(
                errorMessage  = e.message ?: "Something wrong",
            )
        }
    }
}