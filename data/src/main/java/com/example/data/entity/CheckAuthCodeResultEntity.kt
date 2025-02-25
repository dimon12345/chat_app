package com.example.data.entity

import com.example.domain.data.CheckAuthRequestResult
import com.google.gson.annotations.SerializedName

data class CheckAuthCodeResultEntity(
    @SerializedName("refresh_token")
    val refreshToken: String? = null,

    @SerializedName("access_token")
    val accessToken: String? = null,

    @SerializedName("user_id")
    val userId: Int? = null,

    @SerializedName("is_user_exists")
    val isUserExists: Boolean? = null,

    @SerializedName("detail")
    val detail: List<PhoneSyntaxErrorDetailEntity>? = null
) {
    fun toCheckAuthRequestResult(
        success: Boolean,
        serverCode: Int,
        serverError: String): CheckAuthRequestResult {

        return CheckAuthRequestResult(
            success = success,
            errorMessage = serverError,
            code = serverCode,
            refreshToken = refreshToken ?: "",
            accessToken = accessToken ?: "",
            userId = userId ?: 0,
            isUserExists = isUserExists ?: false,
        )
    }
}