package com.example.rekindle.movies.data

import com.example.rekindle.movies.model.Movie
import com.example.rekindle.movies.model.SearchMovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET(".")
    suspend fun searchMovies(@Query("apikey") key: String, @Query("s") query: String): SearchMovieResponse
}