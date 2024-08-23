package com.example.data.entity

import com.example.domain.data.RefreshTokenRequestResult
import com.google.gson.annotations.SerializedName

data class RefreshTokenResultEntity (
    @SerializedName("refresh_token")
    val refreshToken: String? = null,

    @SerializedName("access_token")
    val accessToken: String? = null,

    @SerializedName("user_id")
    val userId: Int? = null,
) {
    fun toRefreshTokenRequestResult(
        success: Boolean,
        serverCode: Int,
        serverError: String
    ): RefreshTokenRequestResult {
        return RefreshTokenRequestResult(
            success = success,
            code = serverCode,
            errorMessage = serverError,
            accessToken = accessToken ?: "",
        )
    }
}