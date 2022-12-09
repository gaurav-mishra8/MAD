package com.example.rekindle.movies.moviedetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.rekindle.R
import com.example.rekindle.movies.model.MovieDetail

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val state = viewModel.state.collectAsState()
    val movieDetailState = state.value

    Box(modifier.fillMaxSize()) {
        if (movieDetailState.isLoading) {
            CircularProgressIndicator()
        } else if (movieDetailState.movieDetail != null) {
            MovieDetail(
                movieDetailState.movieDetail
            )
        }
    }
}

@Composable
fun MovieDetail(
    movieDetail: MovieDetail
) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetail.poster)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = movieDetail.title,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.movie_placeholder),
            fallback = painterResource(R.drawable.movie_placeholder),
            placeholder = painterResource(R.drawable.movie_placeholder),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f)
        )

        Text(
            text = movieDetail.title,
            fontSize = 28.sp,
            fontFamily = FontFamily.Cursive
        )
    }

}