package com.learn.jetmovie.ui

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.learn.jetmovie.ui.screen.home.HomeScreen
import com.learn.jetmovie.ui.theme.JetMovieTheme

@Composable
fun JetMovieApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        HomeScreen(
            onClick = { context, text ->
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
@Preview
fun JetMovieAppPreview() {
    JetMovieTheme {
        JetMovieApp(
            modifier = Modifier
        )
    }
}