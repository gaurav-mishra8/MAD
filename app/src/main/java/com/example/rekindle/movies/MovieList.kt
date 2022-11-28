package com.example.rekindle.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rekindle.movies.model.Movie

@Composable
fun MovieScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
) {
    val state = viewModel.state.collectAsState()
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(value = text, onValueChange = { newText ->
            text = newText
            viewModel.searchMovies(newText.text)
        })

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
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