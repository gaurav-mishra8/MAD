package com.example.rekindle.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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

        Box(
            modifier = Modifier
                .background(color = Color(0xFFFAFAFA))
                .fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
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
    LazyColumn {
        items(items = movies, key = {
            it.id
        }) { movie ->
            Movie(movie)
        }
    }
}

@Composable
fun Movie(movie: Movie) {
    Column {
        Text(text = movie.title, fontWeight = FontWeight.Bold)
        Text(text = "Year: ${movie.year}")
        Spacer(modifier = Modifier.height(16.dp))
    }
}