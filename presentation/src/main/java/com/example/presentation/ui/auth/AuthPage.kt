package com.example.presentation.ui.auth

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R
import com.example.presentation.ui.common.LoadingPage

@Composable
fun AuthPage(
    modifier: Modifier = Modifier,
    authPageViewModel: AuthPageViewModel = viewModel(),
) {
    val authPageState = authPageViewModel.uiState.collectAsState().value
    Box {
        Column(modifier = modifier
            .padding(dimensionResource(id = R.dimen.default_padding))) {
            Text(
                text = stringResource(id = R.string.auth_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            Text(
                text = stringResource(id = R.string.auth_hint),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            TextField(
                modifier = Modifier
                    .fillMaxWidth(1f),
                enabled = !authPageState.showPinNumberAlert,
                value = authPageState.phone,
                onValueChange = { authPageViewModel.onPhoneNumberChanged(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                ),
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            Button(
                modifier = Modifier.fillMaxWidth(1f),
                shape = RectangleShape,
                onClick = { authPageViewModel.onAuthSubmit() },
                enabled = authPageState.sendCodeButtonEnabled,
            ) {
                Text(stringResource(id = R.string.auth_button))
            }
        }

        if (authPageState.showPinNumberAlert) {
            AuthVerificationCodePage()
        }

        if (authPageState.loading) {
            LoadingPage()
        }
    }
}