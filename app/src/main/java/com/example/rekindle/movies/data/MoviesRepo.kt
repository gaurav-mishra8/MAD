package com.example.rekindle.movies.data

import com.example.rekindle.Result
import com.example.rekindle.movies.model.Movie
import com.example.rekindle.movies.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MoviesRepo {
    fun getLatestMovies(): Flow<Result<List<Movie>>>
    fun searchMovie(query: String): Flow<Result<List<Movie>>>
    fun getMovieById(id: String): Flow<Result<MovieDetail>>
}