package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class ProfileAvatarsEntity(
    @SerializedName("avatar")
    val avatar: String? = null,

    @SerializedName("bigAvatar")
    val bigAvatar: String? = null,

    @SerializedName("miniAvatar")
    val miniAvatar: String? = null,
)