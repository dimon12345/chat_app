package com.example.presentation.ui.home.profile_editor

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R
import com.example.presentation.ui.common.TopBar
import com.example.presentation.ui.home.HomePageViewModel

@Composable
fun ProfileEditorPage(
    homePageViewModel: HomePageViewModel = viewModel()
) {
    BackHandler {
        homePageViewModel.onProfileEditorBackPressed()
    }
    Column {
        TopBar(
            title = R.string.profile_editor_title,
            leftImage = R.drawable.ic_back,
            onLeftButtonTap = { homePageViewModel.onProfileEditorBackPressed() }
        )
    }
}