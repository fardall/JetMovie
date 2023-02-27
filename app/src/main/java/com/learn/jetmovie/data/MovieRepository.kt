package com.learn.jetmovie.data

import com.learn.jetmovie.data.remote.api.ApiService
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val apiService: ApiService
) {

    fun searchMovie(query: String) = flow {
        val responseBody = apiService.searchMovie(query).results
        if (responseBody != null) {
            emit(responseBody)
        }
    }
}