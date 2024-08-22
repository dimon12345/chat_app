package com.example.domain.data

data class SendAuthCodeRequestResult (
    val success: Boolean = false,
    val code: Int = 0,
    val errorMessage: String = "",
    val syntaxErrorDetails: List<SyntaxErrorDetail>? = listOf(),
)