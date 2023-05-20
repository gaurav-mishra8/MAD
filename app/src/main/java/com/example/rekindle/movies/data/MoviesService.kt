package com.example.rekindle.movies.data

import com.example.model.movies.MovieDetail
import com.example.model.dto.GetPopularMoviesResponse
import com.example.model.dto.SearchMovieResponse
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