package com.example.rekindle.movies

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rekindle.movies.model.Movie

@Composable
fun MovieScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    val query = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = query.value,
            onValueChange = { newText ->
                query.value = newText
                viewModel.searchMovies(newText)
            },
            modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Search Movies..",
                    style = TextStyle(fontSize = 18.sp, color = Color.DarkGray)
                )
            }
        )

        Spacer(
            modifier = Modifier
                .width(10.dp)
                .height(10.dp)
        )

        Box(contentAlignment = Alignment.Center) {
            if (state.value.isLoading) {
                CircularProgressIndicator()
            } else if (state.value.error != null) {
                Text(text = state.value.error!!)
            } else {
                MovieList(movies = state.value.movies)
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(
            items = movies,
            key = { it.id },
        ) { movie ->
            Movie(movie)
        }
    }
}

@Composable
fun Movie(movie: Movie) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(BorderStroke(2.dp, color = Color.Black)),
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.imgUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                error = painterResource(com.example.rekindle.R.drawable.movie_placeholder),
                fallback = painterResource(com.example.rekindle.R.drawable.movie_placeholder),
                placeholder = painterResource(com.example.rekindle.R.drawable.movie_placeholder)
            )
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                style = TextStyle(textAlign = TextAlign.Center)
            )
            Text(
                text = "Year: ${movie.year}",
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                style = TextStyle(textAlign = TextAlign.Center)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}