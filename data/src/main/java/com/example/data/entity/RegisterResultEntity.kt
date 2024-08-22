package com.example.data.entity

import com.example.domain.data.RegisterRequestResult
import com.google.gson.annotations.SerializedName

data class RegisterResultEntity(
    @SerializedName("refresh_token")
    val refreshToken: String? = null,

    @SerializedName("access_token")
    val accessToken: String? = null,

    @SerializedName("user_id")
    val userId: Long? = null,

    @SerializedName("detail")
    val detail: List<PhoneSyntaxErrorDetailEntity>? = null
) {
    fun toRegisterRequestResult(
        success: Boolean,
        serverCode: Int,
        serverError: String
    ): RegisterRequestResult {
        return RegisterRequestResult(
            success = success,
            errorMessage = serverError,
            code = serverCode,
            refreshToken = refreshToken ?: "",
            accessToken = accessToken ?: "",
            userId = userId ?: 0L,
        )
    }
}