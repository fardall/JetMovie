package com.learn.jetmovie.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.jetmovie.data.MovieRepository
import com.learn.jetmovie.data.remote.response.DetailResponse
import com.learn.jetmovie.ui.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<Result<DetailResponse>>(Result.Loading)
    val uiState: StateFlow<Result<DetailResponse>>
        get() = _uiState

    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            repository.getDetailMovie(movieId)
                .catch {
                    _uiState.value = Result.Error(it.message.toString())
                }
                .collect { movie ->
                    _uiState.value = Result.Success(movie)
                }
        }
    }
}