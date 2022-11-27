package com.example.rekindle.movies.data

import com.example.rekindle.movies.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MoviesRepo @Inject constructor(
    private val moviesService: MoviesService
) {
    fun searchMovie(): Flow<List<Movie>> = flow {
        val movies = moviesService.searchMovies("b10dad57", "world").movies
        emit(movies)
    }
}
