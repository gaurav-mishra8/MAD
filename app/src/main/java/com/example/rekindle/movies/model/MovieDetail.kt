package com.example.rekindle.movies.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("Metascore")
    val metascore: String = "",
    @SerializedName("BoxOffice")
    val boxOffice: String = "",
    @SerializedName("Website")
    val website: String = "",
    @SerializedName("imdbRating")
    val imdbRating: String = "",
    @SerializedName("imdbVotes")
    val imdbVotes: String = "",
    @SerializedName("Runtime")
    val runtime: String = "",
    @SerializedName("Language")
    val language: String = "",
    @SerializedName("Rated")
    val rated: String = "",
    @SerializedName("Production")
    val production: String = "",
    @SerializedName("Released")
    val released: String = "",
    @SerializedName("imdbID")
    val imdbID: String = "",
    @SerializedName("Plot")
    val plot: String = "",
    @SerializedName("Director")
    val director: String = "",
    @SerializedName("Title")
    val title: String = "",
    @SerializedName("Actors")
    val actors: String = "",
    @SerializedName("Response")
    val response: String = "",
    @SerializedName("Type")
    val type: String = "",
    @SerializedName("Awards")
    val awards: String = "",
    @SerializedName("DVD")
    val dvd: String = "",
    @SerializedName("Year")
    val year: String = "",
    @SerializedName("Poster")
    val poster: String = "",
    @SerializedName("Country")
    val country: String = "",
    @SerializedName("Genre")
    val genre: String = "",
    @SerializedName("Writer")
    val writer: String = ""
)