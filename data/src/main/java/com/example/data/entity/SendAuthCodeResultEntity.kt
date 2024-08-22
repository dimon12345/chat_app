package com.example.data.entity

import com.example.domain.data.SendAuthCodeRequestResult
import com.google.gson.annotations.SerializedName

data class SendAuthCodeResultEntity (
    @SerializedName("is_success")
    val isSuccess: Boolean? = null,

    @SerializedName("detail")
    val detail: List<PhoneSyntaxErrorDetailEntity>? = null

) {
    fun toAuthDeviceRequestResult(
        success: Boolean,
        serverCode: Int,
        serverError: String,
    ): SendAuthCodeRequestResult {
        return SendAuthCodeRequestResult(
            code = serverCode,
            success = success,
            errorMessage = serverError,
        )
    }
}