package com.example.rekindle.movies.data

import com.example.rekindle.movies.model.MovieDetail
import com.example.rekindle.movies.model.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET(".")
    suspend fun searchMovies(
        @Query("apikey") key: String,
        @Query("s") query: String
    ): SearchMovieResponse

    @GET(".")
    suspend fun fetchMovieDetails(
        @Query("apikey") key: String,
        @Query("i") query: String
    ): MovieDetail?
}