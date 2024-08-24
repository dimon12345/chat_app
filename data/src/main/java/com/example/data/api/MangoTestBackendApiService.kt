package com.example.data.api

import com.example.data.entity.CheckAuthCodeResultEntity
import com.example.data.entity.GetProfileResultEntity
import com.example.data.entity.RefreshTokenResultEntity
import com.example.data.entity.RegisterResultEntity
import com.example.data.entity.SendAuthCodeResultEntity
import com.example.data.entity.SetProfileResultEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface MangoTestBackendApiService {
    @POST(API_SEND_AUTH_CODE)
    suspend fun sendAuthCode(@Body bodyData: HashMap<String, String>): Response<SendAuthCodeResultEntity>

    @POST(API_CHECK_AUTH_CODE)
    suspend fun checkAuthCode(@Body bodyData: HashMap<String, String>): Response<CheckAuthCodeResultEntity>

    @POST(API_REGISTER_CODE)
    suspend fun register(@Body bodyData: HashMap<String, String>): Response<RegisterResultEntity>

    @POST(API_REFRESH_TOKEN_CODE)
    suspend fun refreshToken(@Body bodyData: HashMap<String, String>): Response<RefreshTokenResultEntity>

    @GET(API_GET_PROFILE_CODE)
    suspend fun getProfile(@Header("Authorization") token: String): Response<GetProfileResultEntity>

    @PUT(API_GET_PROFILE_CODE)
    suspend fun setProfile(
        @Header("Authorization") token: String,
        @Body bodyData: HashMap<String, String>
    ): Response<SetProfileResultEntity>

    companion object {
        const val API_URL: String = "https://plannerok.ru"
        const val API_SEND_AUTH_CODE = "/api/v1/users/send-auth-code/"
        const val API_CHECK_AUTH_CODE = "/api/v1/users/check-auth-code/"
        const val API_REGISTER_CODE = "/api/v1/users/register/"
        const val API_GET_PROFILE_CODE = "/api/v1/users/me/"
        const val API_REFRESH_TOKEN_CODE = "/api/v1/users/refresh-token/"
    }
}