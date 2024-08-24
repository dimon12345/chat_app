package com.example.presentation.ui.home.profile_editor

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R
import com.example.presentation.ui.common.LoadingPage
import com.example.presentation.ui.common.TopBar
import com.example.presentation.ui.home.HomePageViewModel

@Composable
fun ProfileEditorPage(
    homePageViewModel: HomePageViewModel = viewModel(),
    profileEditorPageViewModel: ProfileEditorPageViewModel = viewModel(),
) {
    val state = profileEditorPageViewModel.uiState.collectAsState().value
    BackHandler {
        homePageViewModel.onProfileEditorBackPressed()
    }
    Column {
        TopBar(
            title = R.string.profile_editor_title,
            leftImage = R.drawable.ic_back,
            onLeftButtonTap = { homePageViewModel.onProfileEditorBackPressed() }
        )

        Box {
            Column(
                Modifier.padding(dimensionResource(id = R.dimen.default_padding))
            ) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
                ProfileTextField(
                    info = stringResource(id = R.string.profile_name_info),
                    value = state.profile.name,
                    onValueChange = { profileEditorPageViewModel.onNameChanged(it) },
                    disableInputs = state.loading,
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
                ProfileTextField(
                    info = stringResource(id = R.string.profile_city_info),
                    value = state.profile.city,
                    onValueChange = { profileEditorPageViewModel.onCityChanged(it) },
                    disableInputs = state.loading,
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
                ProfileTextField(
                    info = stringResource(id = R.string.profile_status_info),
                    value = state.profile.status,
                    onValueChange = { profileEditorPageViewModel.onStatusChanged(it) },
                    disableInputs = state.loading,
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
                Button(
                    modifier = Modifier.fillMaxWidth(1f),
                    enabled = state.applyButtonEnabled,
                    onClick = { profileEditorPageViewModel.onApplyClick() },
                    content = {
                        Text(
                            text = stringResource(id = R.string.profile_editor_apply),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    shape = RectangleShape,
                )
            }

            if(state.loading) {
                LoadingPage()
            }
        }
    }
}

@Composable
fun ProfileTextField(
    info: String,
    value: String,
    onValueChange: (String) -> Unit,
    disableInputs: Boolean,
) {
    Column(
        Modifier.height(dimensionResource(id = R.dimen.profile_edit_field_height)),
    ) {
        Text(
            text = info,
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_padding)))

        val focusManager = LocalFocusManager.current
        TextField(
            enabled = !disableInputs,
            modifier = Modifier
                .fillMaxSize(1f),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.text_field_corner_radius)),
            colors = TextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.moveFocus(FocusDirection.Next) },
                onNext = { focusManager.moveFocus(FocusDirection.Next) },
                onPrevious = { focusManager.moveFocus(FocusDirection.Previous) },
            ),
        )
    }
}