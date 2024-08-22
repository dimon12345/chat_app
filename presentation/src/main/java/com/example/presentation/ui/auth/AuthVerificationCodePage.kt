package com.example.presentation.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.R

const val VERIFICATION_CODE_LENGTH = 6

@Composable
fun AuthVerificationCodePage(
    modifier: Modifier = Modifier,
    authPageViewModel: AuthPageViewModel = viewModel(),
) {
    val authPageState = authPageViewModel.uiState.collectAsState().value
    Box(
        modifier = modifier
            .fillMaxSize(1f)
            .background(Color.Black.copy(alpha = .5f))
            .clickable(enabled = false) {},
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .background(MaterialTheme.colorScheme.background),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(dimensionResource(id = R.dimen.default_padding)),
                text = stringResource(id = R.string.auth_verification_code_hint),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(dimensionResource(id = R.dimen.auth_code_indicator_height)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                for (ch in authPageState.code) {
                    CodeNumber(ch)
                }
                for (i in authPageState.code.length..VERIFICATION_CODE_LENGTH - 1) {
                    EmptyNumber()
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

            NumberKeyboard(
                onKeyPressed = { authPageViewModel.onVerificationCodeKey(it)}
            )
        }
    }
}

@Composable
private fun EmptyNumber() {
    Box(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.auth_number_width)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_number_dot),
            contentDescription = null,
        )
    }
}

@Composable
private fun CodeNumber(ch: Char) {
    Box(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.auth_number_width)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = ch.toString(),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun NumberKeyboard(
    onKeyPressed: (String) -> Unit,
) {
    Column {
        val rowModifier = Modifier
            .fillMaxWidth(1f)
            .height(dimensionResource(id = R.dimen.auth_number_key_height))
        val numberModifier = Modifier
            .weight(1f)
            .fillMaxHeight(1f)
        Row(
            modifier = rowModifier
        ) {
            NumberKey(numberModifier, "1", onKeyPressed)
            NumberKey(numberModifier, "2", onKeyPressed)
            NumberKey(numberModifier, "3", onKeyPressed)
        }
        Row(
            modifier = rowModifier
        ) {
            NumberKey(numberModifier, "4", onKeyPressed)
            NumberKey(numberModifier, "5", onKeyPressed)
            NumberKey(numberModifier, "6", onKeyPressed)
        }
        Row(
            modifier = rowModifier
        ) {
            NumberKey(numberModifier, "7", onKeyPressed)
            NumberKey(numberModifier, "8", onKeyPressed)
            NumberKey(numberModifier, "9", onKeyPressed)
        }

        Row(
            modifier = rowModifier
        ) {
            NumberKey(numberModifier, "")
            NumberKey(numberModifier, "0", onKeyPressed)
            NumberKey(numberModifier, "<", onKeyPressed)
        }

    }
}

@Composable
fun NumberKey(
    modifier: Modifier,
    key: String,
    onKeyPressed: ((String) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clickable(
                enabled = onKeyPressed != null,
            ) {
                onKeyPressed?.let { it(key) }
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
    }
}
