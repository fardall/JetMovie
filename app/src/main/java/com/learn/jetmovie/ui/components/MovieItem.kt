package com.learn.jetmovie.ui.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.learn.jetmovie.R
import com.learn.jetmovie.ui.theme.JetMovieTheme
import com.learn.jetmovie.ui.utils.BASE_IMAGE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItem(
    id: Int,
    title: String,
    image: String,
    rating: String,
    releaseDate: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick
    ) {
        Row(
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_IMAGE$image")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sample_avatar),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
            )
            Divider(modifier = Modifier.width(8.dp))
            Column(
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = rating,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = releaseDate,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
@Preview
fun MovieItemPreview() {
    JetMovieTheme {
        MovieItem(
            id = 1,
            title = "Avengers",
            image = "https://image.tmdb.org/t/p/w500/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
            rating = "8.5",
            releaseDate = "2021-04-22",
            onClick = {}
        )
    }
}