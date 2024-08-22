package com.example.presentation.ui.home.chat_list

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R
import com.example.presentation.ui.common.TopBar
import com.example.presentation.ui.home.HomePageViewModel

@Composable
fun ChatListPage(
    homePageViewModel: HomePageViewModel = viewModel(),
) {
    Column {
        TopBar(
            leftImage = R.drawable.ic_profile,
            onLeftButtonTap = { homePageViewModel.onProfile() }
        )
    }
}