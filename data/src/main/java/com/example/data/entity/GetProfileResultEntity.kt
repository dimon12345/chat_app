package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class GetProfileResultEntity(
    @SerializedName("profile_data")
    val profileData: GetProfileDataResultEntity? = null,
)