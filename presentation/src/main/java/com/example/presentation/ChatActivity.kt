package com.example.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.presentation.theme.AppMainTheme
import com.example.presentation.ui.app.AppPage
import com.example.presentation.ui.app.AppStateSelector
import com.example.presentation.ui.app.AppContentType
import com.example.presentation.ui.app.AppViewModel
import com.example.presentation.ui.auth.AuthPageViewModel
import com.example.presentation.ui.auth.ToastNotificator
import com.example.presentation.ui.registration.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity :
    ComponentActivity(),
    ToastNotificator,
    AppStateSelector
{
    private val authPageViewModel: AuthPageViewModel by viewModels()
    private val registrationViewModel: RegistrationViewModel by viewModels()
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authPageViewModel.toastNotificator = this
        authPageViewModel.appStateSelector = this
        registrationViewModel.toastNotificator = this
        registrationViewModel.appStateSelector = this

        setContent {
            AppMainTheme {
                AppPage()
            }
        }
    }

    override fun sendToast(text: String) {
        runOnUiThread {
            Toast
                .makeText(this, text, Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun selectState(appStateType: AppContentType) = appViewModel.selectState(appStateType)
}