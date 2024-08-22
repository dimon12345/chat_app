package com.example.presentation.ui.registration

data class RegistrationPageState(
    val loading: Boolean = false,
    val phone: String = "",
    val name: String = "",
    val username: String = "",
    val submitButtonEnabled: Boolean = false,
)