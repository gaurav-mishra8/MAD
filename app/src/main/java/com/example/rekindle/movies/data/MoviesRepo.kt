package com.example.rekindle.movies.data

import com.example.rekindle.Result
import com.example.rekindle.movies.model.Movie
import com.example.rekindle.movies.model.MovieDetail
import com.example.rekindle.movies.model.SearchResult
import com.example.rekindle.movies.model.SearchResultDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class MoviesRepo @Inject constructor(
    private val moviesService: MoviesService,
    private val searchResultDao: SearchResultDao
) {

    fun searchMovie(query: String): Flow<Result<List<Movie>>> = flow {
        val movies = moviesService.searchMovies("b10dad57", query).movies

        val result: Result<List<Movie>> = if (movies == null) {
            Result.Success(emptyList())
        } else {
            val searchResult = SearchResult(query = query)
            searchResultDao.saveSearchResult(searchResult)
            Result.Success(movies)
        }
        emit(result)
    }.onStart {
        emit(Result.Loading)
    }.catch { exception ->
        emit(Result.Error(exception))
    }

    fun getMovieById(id: String): Flow<Result<MovieDetail>> = flow {
        val movieDetail = moviesService.fetchMovieDetails("b10dad57", id)

        val result: Result<MovieDetail> =
            if (movieDetail == null || movieDetail.response == "False") {
                Result.Error()
            } else {
                Result.Success(movieDetail)
            }
        emit(result)
    }.onStart {
        emit(Result.Loading)
    }.catch { exception ->
        emit(Result.Error(exception = exception))
    }
}
