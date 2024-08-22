package com.example.presentation.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.presentation.registration.RegistrationPage
import com.example.presentation.ui.auth.AuthPage
import com.example.presentation.ui.common.LoadingPage
import com.example.presentation.ui.home.HomePage

@Composable
fun AppPage(
    appPageViewModel: AppViewModel = viewModel(),
) {
    val uiState = appPageViewModel.uiState.collectAsState().value
    when(uiState.currentAppStateType) {
        AppContentType.AUTHORIZATION -> AuthPage()
        AppContentType.REGISTRATION -> RegistrationPage()
        AppContentType.HOME -> HomePage()
        AppContentType.LOADING -> LoadingPage(transparent = false)
    }
}