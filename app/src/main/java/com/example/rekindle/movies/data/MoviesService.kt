package com.example.rekindle.movies.data

import com.example.rekindle.movies.model.GetPopularMoviesResponse
import com.example.rekindle.movies.model.MovieDetail
import com.example.rekindle.movies.model.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): GetPopularMoviesResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): SearchMovieResponse

    @GET("movie/{movieId}")
    suspend fun fetchMovieDetails(
        @Path("movieId") movieId: String
    ): MovieDetail?
}