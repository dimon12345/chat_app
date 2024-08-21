package com.example.domain.data

data class SyntaxErrorDetail(
    val loc: List<String> = listOf(),
    val msg: String = "",
    val type: String = "",
)