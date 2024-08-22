package com.example.data.entity

import com.example.domain.data.CheckAuthRequestResult
import com.google.gson.annotations.SerializedName

data class CheckAuthCodeResultEntity(
    @SerializedName("refresh_token")
    val refreshToken: String? = null,

    @SerializedName("access_token")
    val accessToken: String? = null,

    @SerializedName("user_id")
    val userId: Long? = null,

    @SerializedName("is_user_exists")
    val isUserExists: Boolean? = null,

    @SerializedName("detail")
    val detail: List<PhoneSyntaxErrorDetailEntity>? = null
) {
    fun toCheckAuthRequestResult(
        serverCode: Int,
        serverError: String): CheckAuthRequestResult {

        return CheckAuthRequestResult(
            errorMessage = serverError,
            code = serverCode,
            refreshToken = refreshToken ?: "",
            accessToken = accessToken ?: "",
            userId = userId ?: 0L,
            isUserExists = isUserExists ?: false,
        )
    }
}