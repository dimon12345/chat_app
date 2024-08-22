package com.example.ui.presentation.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R
import com.example.presentation.ui.common.LoadingPage
import com.example.presentation.ui.registration.RegistrationViewModel

@Composable
fun RegistrationPage(
    modifier: Modifier = Modifier,
    registrationViewModel: RegistrationViewModel = viewModel(),
) {
    val state = registrationViewModel.uiState.collectAsState().value
    Box {
        Column(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.default_padding))
        ) {
            Text(
                text = stringResource(id = R.string.registration_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            Text(
                text = stringResource(id = R.string.registration_hint),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            Text(
                text = state.phone,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.fat_padding)))

            TextField(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.registration_input_height))
                    .fillMaxWidth(1f),
                value = state.name,
                onValueChange = { registrationViewModel.onNameChanged(it) },
                enabled = !state.loading,
                colors = TextFieldDefaults.colors().copy(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.text_field_corner_radius)),
                label = {
                    Text(
                        text = stringResource(id = R.string.registration_name)
                    )
                }
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            TextField(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.registration_input_height))
                    .fillMaxWidth(1f),
                value = state.username,
                onValueChange = { registrationViewModel.onUsernameChanged(it) },
                enabled = !state.loading,
                colors = TextFieldDefaults.colors().copy(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.text_field_corner_radius)),
                label = {
                    Text(
                        text = stringResource(id = R.string.registration_username)
                    )
                }
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            Button(
                modifier = Modifier
                    .fillMaxWidth(1f),
                shape = RectangleShape,
                onClick = { registrationViewModel.onSubmit() },
                enabled = state.submitButtonEnabled,
            ) {
                Text(stringResource(id = R.string.registration_button))
            }
        }

        if (state.loading) {
            LoadingPage()
        }
    }
}