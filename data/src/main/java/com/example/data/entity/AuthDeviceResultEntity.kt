package com.example.data.entity

import com.example.domain.data.AuthDeviceRequestResult
import com.google.gson.annotations.SerializedName

data class AuthDeviceResultEntity (
    @SerializedName("is_success")
    val isSuccess: Boolean? = null,

    @SerializedName("detail")
    val detail: List<PhoneSyntaxErrorDetailEntity>? = null

) {
    fun toAuthDeviceRequestResult(
        serverCode: Int,
        serverError: String,
    ): AuthDeviceRequestResult {
        return AuthDeviceRequestResult(
            code = serverCode,
            success = isSuccess ?: false,
            errorMessage = serverError,
        )
    }
}