package com.example.domain.data

data class CheckAuthResult(
    val success: Boolean = false,
    val refreshToken: String = "",
    val accessToken: String = "",
    val userId: Int = 0,
    val isUserExists: Boolean = false,
    val errorText: String = "",
)