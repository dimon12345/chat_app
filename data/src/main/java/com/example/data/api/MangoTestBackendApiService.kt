package com.example.data.api

import com.example.data.entity.AuthDeviceResultEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MangoTestBackendApiService {
    @POST(API_AUTH_DEVICE)
    suspend fun authDevice(@Body bodyData: HashMap<String, String>): Response<AuthDeviceResultEntity>

    companion object {
        const val API_URL: String = "https://plannerok.ru"
        const val API_AUTH_DEVICE = "/api/v1/users/send-auth-code/"
    }
}