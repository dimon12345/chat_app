package com.example.presentation.ui.home.profile

import com.example.domain.data.ProfileData

data class ProfilePageState (
    val loading: Boolean = true,
    val profileEditorEnabled: Boolean = false,
    val profile: ProfileData = ProfileData()
)