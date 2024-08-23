package com.example.data.repository

import com.example.data.api.MangoTestBackendApiService
import com.example.data.entity.CheckAuthCodeResultEntity
import com.example.data.entity.RegisterResultEntity
import com.example.data.entity.SendAuthCodeResultEntity
import com.example.domain.repository.MainRepository
import com.example.domain.data.CheckAuthRequestResult
import com.example.domain.data.RegisterRequestResult
import com.example.domain.data.SendAuthCodeRequestResult
import javax.inject.Inject

class MangoTestBackendMainRepository @Inject constructor(
    private val mangoTestBackendApiService: MangoTestBackendApiService,
) : MainRepository {
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
                success = resultEntity.isSuccess ?: false,
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
                success = response.code() == 200,
                serverCode = response.code(),
                serverError = serverError,
            )

        } catch (e: Exception) {
            CheckAuthRequestResult(
                errorMessage = e.message ?: "Something wrong"
            )
        }
    }

    override suspend fun register(
        phone: String,
        name: String,
        username: String
    ): RegisterRequestResult {
        return try {
            val args = HashMap<String, String>()
            args["phone"] = phone
            args["name"] = name
            args["username"] = username
            val response = mangoTestBackendApiService.register(args)
            val resultEntity: RegisterResultEntity = response.body() ?: RegisterResultEntity()
            val serverError =
                if (response.code() == REGISTER_SUCCESS_CODE) {
                    ""
                } else {
                    "${response.code()}: ${response.errorBody()?.string() ?: ""}"
                }

            val success = response.code() == REGISTER_SUCCESS_CODE
            resultEntity.toRegisterRequestResult(
                success = success,
                serverCode = response.code(),
                serverError = serverError,
            )
        } catch (e: Exception) {
            assert(e.message?.isNotEmpty() == true)
            RegisterRequestResult(
                errorMessage  = e.message ?: "Something wrong",
            )
        }
    }
    companion object {
        const val REGISTER_SUCCESS_CODE = 201
    }
}