package com.example.presentation.ui.home.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R
import com.example.presentation.ui.common.LoadingPage
import com.example.presentation.ui.common.TopBar
import com.example.presentation.ui.home.HomePageViewModel

@Composable
fun ProfilePage(
    homePageViewModel: HomePageViewModel = viewModel(),
    profilePageViewModel: ProfilePageViewModel = viewModel(),
) {
    val state = profilePageViewModel.uiState.collectAsState().value
    BackHandler {
        homePageViewModel.onProfileBackPressed()
    }

    Box {
        Column {
            TopBar(
                title = R.string.profile_title,
                leftImage = R.drawable.ic_back,
                rightImage = R.drawable.ic_edit,
                onLeftButtonTap = { homePageViewModel.onProfileBackPressed() },
                onRightButtonTap = {
                    if (state.profileEditorEnabled) {
                        homePageViewModel.onProfileEditPressed()
                    }
                }
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))
            ProfileText(stringResource(id = R.string.profile_username), state.profile.username)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_padding)))
            ProfileText(stringResource(id = R.string.profile_phone), state.profile.phone)

            Spacer(Modifier.height(dimensionResource(id = R.dimen.fat_padding)))
            ProfileText(stringResource(id = R.string.profile_name), state.profile.name)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_padding)))
            ProfileText(stringResource(id = R.string.profile_city), state.profile.city)

//            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_padding)))
//            ProfileText(stringResource(id = R.string.profile_birthday), state.profile.birthday)

//        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_padding)))
//        ProfileText(stringResource(id = R.string.profile_sign), "Virgo")

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_padding)))
            StatusText(state.profile.status)
        }

        if (state.loading) {
            LoadingPage()
        }
    }
}

@Composable
fun StatusText(status: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(dimensionResource(id = R.dimen.default_padding)),
    ){
        Text(
            text = stringResource(id = R.string.profile_status),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = status,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
private fun ProfileText(title: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(
                horizontal = dimensionResource(id = R.dimen.default_padding),
            ),
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}