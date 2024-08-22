package com.example.data.repository

import com.example.data.api.MangoTestBackendApiService
import com.example.data.entity.CheckAuthCodeResultEntity
import com.example.data.entity.SendAuthCodeResultEntity
import com.example.domain.auth.AuthRepository
import com.example.domain.data.CheckAuthRequestResult
import com.example.domain.data.SendAuthCodeRequestResult
import javax.inject.Inject

class MangoTestBackendAuthRepository @Inject constructor(
    private val mangoTestBackendApiService: MangoTestBackendApiService,
) : AuthRepository {
    override suspend fun sendAuthCode(
        phone: String
    ): SendAuthCodeRequestResult {
        return try {
            val args = HashMap<String, String>()
            args["phone"] = phone
            val response = mangoTestBackendApiService.sendAuthCode(args)
            val resultEntity: SendAuthCodeResultEntity = response.body() ?: SendAuthCodeResultEntity()
            val serverError = response.errorBody()?.string() ?: ""
            resultEntity.toAuthDeviceRequestResult(
                serverCode = response.code(),
                serverError = serverError,
            )
        } catch (e: Exception) {
            assert(e.message?.isNotEmpty() == true)
            SendAuthCodeRequestResult(
                errorMessage  = e.message ?: "Something wrong",
            )
        }
    }

    override suspend fun checkAuthCode(phone: String, code: String): CheckAuthRequestResult {
        return try {
            val args = HashMap<String, String>()
            args["phone"] = phone
            args["code"] = code
            val response = mangoTestBackendApiService.checkAuthCode(args)
            val resultEntity: CheckAuthCodeResultEntity = response.body() ?: CheckAuthCodeResultEntity()
            val serverError = response.errorBody()?.string() ?: ""
            resultEntity.toCheckAuthRequestResult(
                serverCode = response.code(),
                serverError = serverError,
            )

        } catch (e: Exception) {
            CheckAuthRequestResult(
                errorMessage = e.message ?: "Something wrong"
            )
        }
    }
}