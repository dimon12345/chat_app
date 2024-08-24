package com.example.domain.data

data class GetProfileDataRequestResult(
    val success: Boolean = false,
    val code: Int = 0,
    val errorMessage: String = "",
    val profile: ProfileData = ProfileData(),
)