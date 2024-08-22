package com.example.domain.data

data class CheckAuthRequestResult(
    val success: Boolean = false,
    val code: Int = 0,
    val errorMessage: String = "",
    val syntaxErrorDetails: List<SyntaxErrorDetail>? = listOf(),
    val refreshToken: String = "",
    val accessToken: String = "",
    val userId: Long = 0L,
    val isUserExists: Boolean = false,
)