package com.learn.jetmovie.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.learn.jetmovie.R
import com.learn.jetmovie.data.local.entity.DetailItem
import com.learn.jetmovie.data.remote.response.DetailResponse
import com.learn.jetmovie.ui.ViewModelFactory
import com.learn.jetmovie.ui.theme.JetMovieTheme
import com.learn.jetmovie.ui.utils.BASE_IMAGE
import com.learn.jetmovie.ui.utils.Result
import com.learn.jetmovie.ui.utils.errorText

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
    movieId: String,
    onBackArrowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    viewModel.uiState.collectAsState(initial = Result.Loading).value.let { result ->
        when (result) {
            is Result.Loading -> {
                viewModel.getDetailMovie(movieId.toInt())
            }
            is Result.Success -> {
                DetailContent(
                    result.data,
                    onBackArrowClick,
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
fun DetailContent(
    movie: DetailResponse,
    onBackArrowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val items = listOf(
            DetailItem(
                icon = Icons.Default.Star,
                text = "Rating",
                value = movie.voteCount.toString()
            )
        )
        Row {
            IconButton(
                onClick = { onBackArrowClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(text = "Movie Detail")
        }
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_IMAGE${movie.posterPath}")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sample_avatar),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(150.dp).height(200.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                for (item in items) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.text
                    )
                    Text(text = item.text)
                    Text(text = item.value)
                }
            }
        }
        Text(text = movie.title.toString())
        Divider(modifier = Modifier.fillMaxWidth())
        Text(text = "Synopsis")
        Text(text = movie.overview.toString())
    }
}

@Composable
@Preview
fun DetailScreenPreview() {
    JetMovieTheme {
        DetailScreen(movieId = "1", onBackArrowClick = { })
    }
}