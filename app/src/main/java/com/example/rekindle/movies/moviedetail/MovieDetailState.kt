package com.example.rekindle.movies.moviedetail

import com.example.model.movies.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val error: String? = null
)
