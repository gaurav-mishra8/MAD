package com.example.rekindle.movies.moviedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.model.movies.MovieDetail
import com.example.rekindle.R

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    val movieDetailState = state.value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
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
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetail.getBackdropUrl())
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
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = movieDetail.overview,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )
        if (movieDetail.release_date.isNotEmpty()) {
            Text(
                text = "Release Date: ${movieDetail.release_date}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }

}