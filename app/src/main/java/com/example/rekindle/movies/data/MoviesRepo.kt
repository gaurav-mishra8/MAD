package com.example.rekindle.movies.data

import com.example.rekindle.Result
import com.example.rekindle.movies.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class MoviesRepo @Inject constructor(
    private val moviesService: MoviesService
) {
    fun searchMovie(query: String): Flow<Result<List<Movie>>> = flow {
        val movies = moviesService.searchMovies("b10dad57", query).movies

        val result: Result<List<Movie>> = if (movies == null) {
            Result.Success(emptyList())
        } else {
            Result.Success(movies)
        }
        emit(result)
    }.onStart {
        emit(Result.Loading)
    }.catch { exception ->
        emit(Result.Error(exception))
    }
}
