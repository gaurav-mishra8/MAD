package com.example.rekindle.movies.model

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>?,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_result") val totalResults: Int
)

data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("original_title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val description: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String
) {

    fun getPosterUrl(): String {
        return "$IMAGE_BASE_URL${posterPath}"
    }

    fun getBackdropUrl(): String {
        return "$IMAGE_BASE_URL${backdropPath}"
    }
}

data class GetPopularMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>?,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_result") val totalResults: Int
)

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"