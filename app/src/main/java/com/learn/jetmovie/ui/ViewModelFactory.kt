package com.learn.jetmovie.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.jetmovie.data.MovieRepository
import com.learn.jetmovie.ui.screen.detail.DetailViewModel
import com.learn.jetmovie.ui.screen.home.HomeViewModel
import com.learn.jetmovie.ui.utils.Injection

class ViewModelFactory private constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(movieRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(movieRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }.also { instance = it }
    }
}