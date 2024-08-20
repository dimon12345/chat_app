package com.example.presentation.ui.auth

data class AuthPageState (
    val country: String? = null,
    val phoneNumber: String = "",
    val sendCodeButtonEnabled: Boolean = false,
)