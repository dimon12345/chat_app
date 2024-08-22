package com.example.presentation.ui.common

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.presentation.R

@Composable
fun LoadingPage(
    modifier: Modifier = Modifier,
    transparent: Boolean = true,
) {
    val color = if (transparent) {
        Color.Black.copy(alpha = .5f)
    } else {
        Color.Black
    }

    Box(
        modifier = modifier
            .fillMaxSize(1f)
            .background(color)
            .clickable(enabled = false) {},
    ) {
        AndroidView(
            modifier = Modifier
                .align(Alignment.Center)
                .size(
                    width = dimensionResource(id = R.dimen.loading_image_size),
                    height = dimensionResource(id = R.dimen.loading_image_size),
                ),
            factory = { context ->
                ImageView(context).apply {
                    setImageResource(R.drawable.loading_animation)
                }
            },
        )
    }
}