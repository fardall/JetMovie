package com.learn.jetmovie.ui.utils

import android.content.Context
import android.widget.Toast

const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}