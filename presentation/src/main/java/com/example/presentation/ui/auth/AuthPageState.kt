package com.example.presentation.ui.auth

data class AuthPageState (
    val loading: Boolean = false,
    val country: String? = null,
    val phoneNumber: String = "",
    val sendCodeButtonEnabled: Boolean = false,
    val showPinNumberAlert: Boolean = false,
)