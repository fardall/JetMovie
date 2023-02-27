package com.learn.jetmovie.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
}