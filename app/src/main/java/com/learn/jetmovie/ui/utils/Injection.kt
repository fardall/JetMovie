package com.learn.jetmovie.ui.utils

import com.learn.jetmovie.data.MovieRepository
import com.learn.jetmovie.data.remote.api.ApiConfig

object Injection {

    fun provideRepository(): MovieRepository {
        val apiService = ApiConfig.getApiService()
        return MovieRepository(apiService)
    }
}