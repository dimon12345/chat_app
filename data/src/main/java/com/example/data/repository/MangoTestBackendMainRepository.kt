package com.example.data.repository

import com.example.data.api.MangoTestBackendApiService
import com.example.data.entity.CheckAuthCodeResultEntity
import com.example.data.entity.GetProfileDataResultEntity
import com.example.data.entity.RefreshTokenResultEntity
import com.example.data.entity.RegisterResultEntity
import com.example.data.entity.SendAuthCodeResultEntity
import com.example.data.entity.SetProfileResultEntity
import com.example.domain.repository.MainRepository
import com.example.domain.data.CheckAuthRequestResult
import com.example.domain.data.GetProfileDataRequestResult
import com.example.domain.data.ProfileData
import com.example.domain.data.RefreshTokenRequestResult
import com.example.domain.data.RegisterRequestResult
import com.example.domain.data.SendAuthCodeRequestResult
import com.example.domain.data.SetProfileDataRequestResult
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
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
            val success = response.code() == REGISTER_SUCCESS_CODE
            val serverError =
                if (success) {
                    ""
                } else {
                    "${response.code()}: ${response.errorBody()?.string() ?: ""}"
                }

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

    override suspend fun getProfile(token: String): GetProfileDataRequestResult {
        return try {
            val response = mangoTestBackendApiService.getProfile("Bearer $token")
            val success = response.code() == REGISTER_SUCCESS_CODE
            val serverError =
                if (success) {
                    ""
                } else {
                    "${response.code()}: ${response.errorBody()?.string() ?: ""}"
                }

            val resultEntity: GetProfileDataResultEntity =
                response.body()?.profileData ?: GetProfileDataResultEntity()
            resultEntity.toGetProfileDataRequestResult(
                success = success,
                serverCode = response.code(),
                serverError = serverError,
            )
        } catch(e: Exception) {
            GetProfileDataRequestResult(
                errorMessage = e.message ?: "Something wrong"
            )
        }
    }

    override suspend fun updateProfile(
        token: String,
        profile: ProfileData
    ): SetProfileDataRequestResult {
        return try {
            val args = HashMap<String, String>()
            with(profile) {
                args["name"] = name
                args["username"] = username
                //args["birthday"] = birthday
                args["city"] = city
                args["status"] = status
            }

            val response = mangoTestBackendApiService.setProfile("Bearer $token", args)
            val success = response.code() == SET_PROFILE_SUCCESS_CODE
            val serverError =
                if (success) {
                    ""
                } else {
                    "${response.code()}: ${response.errorBody()?.string() ?: ""}"
                }
            val resultEntity: SetProfileResultEntity =
                response.body() ?: SetProfileResultEntity()
            resultEntity.toSetProfileDataRequestResult(
                success = success,
                serverCode = response.code(),
                serverError = serverError,
            )
        } catch(e: Exception) {
            SetProfileDataRequestResult(
                errorMessage = e.message ?: "Something wrong"
            )
        }
    }

    override suspend fun refreshToken(refreshToken: String, accessToken: String): RefreshTokenRequestResult {
        return refreshTokenMutex.withLock {
            try {
                if (lastAccessToken.isNotEmpty() && lastAccessToken != accessToken) {
                    return RefreshTokenRequestResult(
                        success = true,
                        accessToken = lastAccessToken,
                    )
                }

                val args = HashMap<String, String>()
                args["refresh_token"] = refreshToken
                val response = mangoTestBackendApiService.refreshToken(args)
                val resultEntity: RefreshTokenResultEntity =
                    response.body() ?: RefreshTokenResultEntity()
                val success = response.code() == REFRESH_TOKEN_SUCCESS_CODE
                val serverError =
                    if (success) {
                        assert(resultEntity.accessToken?.isNotEmpty() == true)
                        ""
                    } else {
                        "${response.code()}: ${response.errorBody()?.string() ?: ""}"
                    }

                if (resultEntity.accessToken?.isNotEmpty() == true) {
                    lastAccessToken = resultEntity.accessToken
                }

                return resultEntity.toRefreshTokenRequestResult(
                    success = success,
                    serverCode = response.code(),
                    serverError = serverError,
                )
            } catch (e: Exception) {
                RefreshTokenRequestResult(
                    errorMessage = e.message ?: " Something wrong"
                )
            }
        }
    }

    companion object {
        const val REGISTER_SUCCESS_CODE = 201
        const val REFRESH_TOKEN_SUCCESS_CODE = 200
        const val SET_PROFILE_SUCCESS_CODE = 200

        val refreshTokenMutex = Mutex()
        var lastAccessToken: String = ""
    }
}