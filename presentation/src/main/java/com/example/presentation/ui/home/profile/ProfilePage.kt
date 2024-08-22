package com.example.presentation.ui.home.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R
import com.example.presentation.ui.common.TopBar
import com.example.presentation.ui.home.HomePageViewModel

@Composable
fun ProfilePage(
    homePageViewModel: HomePageViewModel = viewModel()
) {
    BackHandler {
        homePageViewModel.onProfileBackPressed()
    }

    Column {
        TopBar(
            title = R.string.profile_title,
            leftImage = R.drawable.ic_back,
            rightImage = R.drawable.ic_edit,
            onLeftButtonTap = { homePageViewModel.onProfileBackPressed() },
            onRightButtonTap = { homePageViewModel.onProfileEditPressed() }
        )
    }
}