package com.example.presentation.ui.home.profile_editor

import com.example.domain.data.ProfileData

data class ProfileEditorPageState(
    val loading: Boolean = true,
    val profile: ProfileData = ProfileData(),
    val applyButtonEnabled: Boolean = false,
)