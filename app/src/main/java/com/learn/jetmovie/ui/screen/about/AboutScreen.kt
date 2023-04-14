package com.learn.jetmovie.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.learn.jetmovie.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.self),
            contentDescription = "About Screen"
        )
        Text(
            text = "Muhamad Fardal Akter Min Gali"
        )
        Text(
            text = "aafardal8@gmail.com"
        )
    }
}