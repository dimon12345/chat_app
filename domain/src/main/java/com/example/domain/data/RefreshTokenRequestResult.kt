package com.example.domain.data

data class RefreshTokenRequestResult(
    val success: Boolean = false,
    val code: Int = 0,
    val errorMessage: String = "",
    val accessToken: String = "",
)
