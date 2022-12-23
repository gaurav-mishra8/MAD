package com.example.rekindle.movies.movieslist

import com.example.rekindle.movies.model.Movie

data class MoviesState(
    val movieDTOS: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSearchMode: Boolean = false
)
