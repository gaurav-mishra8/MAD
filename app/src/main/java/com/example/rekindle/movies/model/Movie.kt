package com.example.rekindle.movies.model

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("Search") val movies: List<Movie>?,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Error") val error: String?

)

data class Movie(
    @SerializedName("imdbID") val id: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val imgUrl: String
)