package com.example.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.presentation.theme.AppMainTheme
import com.example.presentation.ui.app.AppPage
import com.example.presentation.ui.app.AppPageViewModel
import com.example.presentation.ui.auth.AuthPageViewModel
import com.example.presentation.ui.home.HomePageViewModel
import com.example.presentation.ui.home.profile_editor.ProfileEditorPageViewModel
import com.example.presentation.ui.registration.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {
    private val authPageViewModel: AuthPageViewModel by viewModels()
    private val registrationViewModel: RegistrationViewModel by viewModels()
    private val appPageViewModel: AppPageViewModel by viewModels()
    private val profileEditorPageViewModel: ProfileEditorPageViewModel by viewModels()
    private val homePageViewModel: HomePageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authPageViewModel.toaster.observe(this) { sendToast(it) }
        authPageViewModel.appContentType.observe(this) { appPageViewModel.selectState(it) }

        registrationViewModel.toaster.observe(this) { sendToast(it) }
        registrationViewModel.appContentType.observe(this) { appPageViewModel.selectState(it) }

        // home page
        profileEditorPageViewModel.toaster.observe(this) { sendToast(it) }
        profileEditorPageViewModel.content.observe(this) { homePageViewModel.selectContent(it) }

        setContent {
            AppMainTheme {
                AppPage()
            }
        }
    }

    private fun sendToast(text: String) {
        runOnUiThread {
            Toast
                .makeText(this, text, Toast.LENGTH_LONG)
                .show()
        }
    }
}