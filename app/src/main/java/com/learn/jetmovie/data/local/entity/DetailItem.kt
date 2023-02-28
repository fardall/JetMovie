package com.learn.jetmovie.data.local.entity

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class DetailItem(
    val icon: ImageVector,
    val text: String,
    val value: String
)