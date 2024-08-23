package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class ProfileResultEntity(
    @SerializedName("profile_data")
    val profileData: ProfileDataResultEntity? = null,
)