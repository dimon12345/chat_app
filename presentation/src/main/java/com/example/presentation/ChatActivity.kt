package com.example.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.presentation.ui.auth.AuthPage
import com.example.presentation.ui.auth.AuthPageViewModel
import com.example.presentation.ui.auth.ToastNotificator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : ComponentActivity(), ToastNotificator {
    val authPageViewModel: AuthPageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authPageViewModel.toastNotificator = this
        setContent {
            AuthPage()
        }
    }

    override fun sendToast(text: String) {
        runOnUiThread {
            Toast
                .makeText(this, text, Toast.LENGTH_LONG)
                .show()
        }
    }
}