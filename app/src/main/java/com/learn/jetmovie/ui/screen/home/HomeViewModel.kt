package com.learn.jetmovie.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.jetmovie.data.MovieRepository
import com.learn.jetmovie.data.remote.response.ResultsItem
import kotlinx.coroutines.flow.MutableStateFlow
import com.learn.jetmovie.ui.utils.Result
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<List<ResultsItem>>>(Result.Loading)
    val uiState: StateFlow<Result<List<ResultsItem>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun setQuery(query: String) {
        _query.value = query
    }

    fun searchMovie() {
        viewModelScope.launch {
            repository.searchMovie(_query.value)
                .catch {
                    _uiState.value = Result.Error(it.message.toString())
                }
                .collect { movies ->
                    _uiState.value = Result.Success(movies)
                }
        }
    }
}