package com.example.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.ui.home.chat.ChatPage
import com.example.presentation.ui.home.chat_list.ChatListPage
import com.example.presentation.ui.home.profile.ProfilePage
import com.example.presentation.ui.home.profile_editor.ProfileEditorPage

@Composable
fun HomePage(
    homePageViewModel: HomePageViewModel = viewModel()
) {
    val state = homePageViewModel.uiState.collectAsState().value
    Box {
        when(state.contentType) {
            HomePageContentType.CHAT_LIST -> ChatListPage()
            HomePageContentType.CHAT -> ChatPage()
            HomePageContentType.PROFILE -> ProfilePage()
            HomePageContentType.PROFILE_EDITOR -> ProfileEditorPage()
        }
    }
}