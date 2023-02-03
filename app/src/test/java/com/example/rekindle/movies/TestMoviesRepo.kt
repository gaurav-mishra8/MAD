package com.example.rekindle.movies

import com.example.rekindle.Result
import com.example.rekindle.movies.data.MoviesRepo
import com.example.rekindle.movies.model.Movie
import com.example.rekindle.movies.model.MovieDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import okhttp3.internal.http.RealResponseBody
import retrofit2.HttpException
import retrofit2.Response

class TestMoviesRepo : MoviesRepo {

    var returnError = false

    override fun getLatestMovies(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading)
        delay(100)
        if (returnError) {
            emit(Result.Error(NullPointerException(ERROR_MESSAGE)))
        } else {
            emit(Result.Success(movieList))
        }
    }

    override fun searchMovie(query: String): Flow<Result<List<Movie>>> = flow {
        if (returnError) {
            emit(Result.Error(NullPointerException(ERROR_MESSAGE)))
        } else {
            emit(Result.Success(searchMovieList))
        }
    }

    override fun getMovieById(id: String): Flow<Result<MovieDetail>> {
        TODO("Not yet implemented")
    }

    companion object {
        const val ERROR_MESSAGE = "exception"
    }
}