package com.example.rekindle.movies.data

import com.example.model.movies.MovieDetail
import com.example.rekindle.Result
import com.example.rekindle.movies.data.db.LatestMoviesDao
import com.example.rekindle.movies.data.db.SearchResultDao
import com.example.rekindle.movies.model.Movie
import com.example.rekindle.movies.model.SearchQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val searchResultDao: SearchResultDao,
    private val latestMoviesDao: LatestMoviesDao
) : MoviesRepo {

    override fun getLatestMovies(): Flow<Result<List<Movie>>> = flow {
        val cachedMovies = latestMoviesDao.getLatestMovies()
        if (cachedMovies.isNotEmpty()) {
            emit(Result.Success(cachedMovies))
        } else {
            val moviesDTO = moviesService.getPopularMovies().results

            val movies = moviesDTO?.map {
                Movie.toMovie(it)
            }

            val result: Result<List<Movie>> = if (movies?.isEmpty() == true) {
                Result.Success(emptyList())
            } else {
                latestMoviesDao.insertLatestMovies(movies!!)
                Result.Success(movies)
            }
            emit(result)
        }
    }.onStart {
        emit(Result.Loading)
    }.catch { exception ->
        emit(Result.Error(exception))
    }

    override fun searchMovie(query: String): Flow<Result<List<Movie>>> = flow {
        val moviesDTO = moviesService.searchMovies(query).results

        val movies = moviesDTO?.map {
            Movie.toMovie(it)
        }

        val result: Result<List<Movie>> = if (movies == null) {
            Result.Success(emptyList())
        } else {
            val searchResult = SearchQuery(query = query)
            searchResultDao.saveSearchResult(searchResult)
            Result.Success(movies)
        }
        emit(result)
    }.onStart {
        emit(Result.Loading)
    }.catch { exception ->
        emit(Result.Error(exception))
    }

    override fun getMovieById(id: String): Flow<Result<MovieDetail>> = flow {
        val movieDetail = moviesService.fetchMovieDetails(id)

        val result: Result<MovieDetail> =
            if (movieDetail == null) {
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
