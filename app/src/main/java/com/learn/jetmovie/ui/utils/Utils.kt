package com.learn.jetmovie.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"

@Composable
fun errorText(error: String) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = error,
            style = MaterialTheme.typography.subtitle2
        )
    }
}