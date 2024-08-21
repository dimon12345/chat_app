package com.example.presentation.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.presentation.R

@Composable
fun PinNumberAlertDialog(
    modifier: Modifier = Modifier,
) {
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
                    .padding(dimensionResource(id = R.dimen.default_padding)),
                text = "Enter pin number",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}