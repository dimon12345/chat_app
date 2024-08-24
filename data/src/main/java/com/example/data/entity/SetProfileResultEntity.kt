package com.example.data.entity

import com.example.domain.data.SetProfileDataRequestResult
import com.google.gson.annotations.SerializedName

data class SetProfileResultEntity(
    @SerializedName("avatars")
    val avatars: ProfileAvatarsEntity? = null,
) {
    fun toSetProfileDataRequestResult(
        success: Boolean,
        serverCode: Int,
        serverError: String
    ): SetProfileDataRequestResult {
        return SetProfileDataRequestResult(
            success = success,
            code = serverCode,
            errorMessage = serverError,
        )
    }
}