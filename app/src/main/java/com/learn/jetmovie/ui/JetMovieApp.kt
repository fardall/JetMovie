package com.learn.jetmovie.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learn.jetmovie.ui.screen.detail.DetailScreen
import com.learn.jetmovie.ui.screen.home.HomeScreen
import com.learn.jetmovie.ui.theme.JetMovieTheme

@Composable
fun JetMovieApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(
                    onClick = { movieId ->
                        navController.navigate(Screen.Detail.createRoute(movieId))
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            composable(route = Screen.Detail.route) {
                DetailScreen(
                    movieId = it.arguments?.getString("movieId")!!,
                    onBackArrowClick = {
                        navController.navigateUp()
                    }
                )
            }
    }
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