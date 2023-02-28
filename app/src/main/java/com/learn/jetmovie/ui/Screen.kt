package com.learn.jetmovie.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("home/{movieId}") {
        fun createRoute(movieId: String) = "home/$movieId"
    }
}