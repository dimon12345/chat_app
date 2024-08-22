package com.example.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.presentation.R

@Composable
fun TopBar(
    title: Int? = null,
    leftImage: Int? = null,
    rightImage: Int? = null,
    onLeftButtonTap: () -> Unit = {},
    onRightButtonTap: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(MaterialTheme.colorScheme.primary)
                .height(dimensionResource(id = R.dimen.top_bar_height))
        ) {
            if (leftImage != null) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { onLeftButtonTap.invoke() }
                        .padding(dimensionResource(id = R.dimen.half_padding)),
                    painter = painterResource(id = leftImage),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (rightImage != null) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { onRightButtonTap.invoke() }
                        .padding(dimensionResource(id = R.dimen.half_padding)),
                    painter = painterResource(id = rightImage),
                    contentDescription = null
                )
            }
        }

        if (title != null) {
            Text(
                text = stringResource(id = title),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }
}