package com.example.data.entity

import com.example.domain.data.ProfileData
import com.example.domain.data.ProfileDataRequestResult
import com.google.gson.annotations.SerializedName

data class ProfileDataResultEntity(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("birthday")
    val birthday: String? = null,

    @SerializedName("city")
    val city: String? = null,

    @SerializedName("avatar")
    val avatar: String? = null,

    @SerializedName("id")
    val userId: Int? = null,

    @SerializedName("phone")
    val phone: String? = null,
) {
    fun toProfileDataRequestResult(
        success: Boolean,
        serverCode: Int,
        serverError: String
    ): ProfileDataRequestResult {
        return ProfileDataRequestResult(
            success = success,
            code = serverCode,
            errorMessage = serverError,
            profile = ProfileData(
                name = name ?: "",
                username = username ?: "",
                birthday = birthday ?: "",
                city = city ?: "",
                avatar = avatar ?: "",
                userId = userId ?: 0,
                phone = phone ?: "",
            ),
        )
    }
}