package com.learn.jetmovie.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.jetmovie.data.remote.response.ResultsItem
import com.learn.jetmovie.ui.ViewModelFactory
import com.learn.jetmovie.ui.components.MovieItem
import com.learn.jetmovie.ui.components.SearchBar
import com.learn.jetmovie.ui.utils.Result
import com.learn.jetmovie.ui.utils.showToast

@Suppress("UNCHECKED_CAST")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
    onClickItem: (String) -> Unit,
    onClickIcon: () -> Unit,
    modifier: Modifier
) {
    val query by viewModel.query
    val context = LocalContext.current
    viewModel.uiState.collectAsState(initial = Result.Loading).value.let { result ->
        when (result) {
            is Result.Loading -> {
                viewModel.searchMovie()
            }
            is Result.Success -> {
                HomeContent(
                    movies = result.data,
                    query = query,
                    onQueryChange = { value ->
                        viewModel.setQuery(value)
                    },
                    onClickItem = onClickItem,
                    onClickIcon = onClickIcon,
                    onSearch = {
                        viewModel.searchMovie()
                    },
                    modifier = modifier
                )
            }
            is Result.Error -> {
                showToast(context, msg = result.error)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    movies: List<ResultsItem>,
    query: String,
    onQueryChange: (String) -> Unit,
    onClickItem: (String) -> Unit,
    onClickIcon: () -> Unit,
    onSearch: KeyboardActionScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            stickyHeader {
                SearchBar(
                    value = query,
                    onValueChange = onQueryChange,
                    onClickIcon = onClickIcon,
                    onSearch = onSearch
                )
            }
            if (movies.isEmpty()) item {
                Text(
                    text = "Data Not Found",
                    style = MaterialTheme.typography.h5
                )
            }
            items(movies, key = { it.id }) { movie ->
                MovieItem(
                    title = movie.originalTitle!!,
                    image = movie.posterPath!!,
                    rating = movie.voteAverage.toString(),
                    releaseDate = movie.releaseDate!!,
                    onClick = {
                        onClickItem(movie.id.toString())
                    }
                )
            }
        }
    }
}