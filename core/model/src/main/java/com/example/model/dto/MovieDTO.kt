package com.example.model.dto

import com.example.constants.IMAGE_BASE_URL
import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDTO>?,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_result") val totalResults: Int
)

data class MovieDTO(
    @SerializedName("id") val id: String,
    @SerializedName("original_title") val title: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("overview") val description: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String
) {

    val getPosterUrl: String
        get() = "$IMAGE_BASE_URL${posterPath}"

}

data class GetPopularMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDTO>?,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_result") val totalResults: Int
)