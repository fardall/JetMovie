package com.learn.jetmovie.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.jetmovie.data.remote.response.ResultsItem
import com.learn.jetmovie.ui.ViewModelFactory
import com.learn.jetmovie.ui.components.MovieItem
import com.learn.jetmovie.ui.utils.Result
import com.learn.jetmovie.ui.utils.errorText

@Suppress("UNCHECKED_CAST")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
    onClick: (String) -> Unit,
    modifier: Modifier
) {
    viewModel.uiState.collectAsState(initial = Result.Loading).value.let { result ->
        when (result) {
            is Result.Loading -> {
                viewModel.searchMovie("One Piece")
            }
            is Result.Success -> {
                HomeContent(
                    movies = result.data as List<ResultsItem>,
                    onClick = onClick,
                    modifier = modifier
                )
            }
            is Result.Error -> {
                errorText(error = result.error)
            }
        }
    }
}

@Composable
fun HomeContent(
    movies: List<ResultsItem>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(movies, key = { it.id!! }) { movie ->
            MovieItem(
                title = movie.originalTitle!!,
                image = movie.posterPath!!,
                rating = movie.voteAverage.toString(),
                releaseDate = movie.releaseDate!!,
                onClick = {
                    onClick(movie.id.toString())
                }
            )
        }
    }
}