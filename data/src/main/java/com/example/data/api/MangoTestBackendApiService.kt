package com.example.data.api

import com.example.data.entity.CheckAuthCodeResultEntity
import com.example.data.entity.RegisterResultEntity
import com.example.data.entity.SendAuthCodeResultEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MangoTestBackendApiService {
    @POST(API_SEND_AUTH_CODE)
    suspend fun sendAuthCode(@Body bodyData: HashMap<String, String>): Response<SendAuthCodeResultEntity>

    @POST(API_CHECK_AUTH_CODE)
    suspend fun checkAuthCode(@Body bodyData: HashMap<String, String>): Response<CheckAuthCodeResultEntity>

    @POST(REGISTER)
    suspend fun register(@Body bodyData: HashMap<String, String>): Response<RegisterResultEntity>

    companion object {
        const val API_URL: String = "https://plannerok.ru"
        const val API_SEND_AUTH_CODE = "/api/v1/users/send-auth-code/"
        const val API_CHECK_AUTH_CODE = "/api/v1/users/check-auth-code/"
        const val REGISTER = "/api/v1/users/register/"
    }
}