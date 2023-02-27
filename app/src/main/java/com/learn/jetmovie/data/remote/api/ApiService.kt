package com.learn.jetmovie.data.remote.api

import com.learn.jetmovie.data.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = "2e65e6aebb956bb2e536092ff5bd715b",
    ): SearchResponse
}