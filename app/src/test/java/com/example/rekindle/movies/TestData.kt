package com.example.rekindle.movies

import com.example.rekindle.movies.data.dto.MovieDTO
import com.example.rekindle.movies.model.Movie

val movieDTOLists = listOf(
    MovieDTO(
        id = "1",
        title = "hello",
        releaseDate = "2012",
        description = "hello movie",
        posterPath = "url1",
        backdropPath = "url11"
    ),
    MovieDTO(
        id = "2",
        title = "world",
        releaseDate = "2013",
        description = "world movie",
        posterPath = "url2",
        backdropPath = "url22"
    )
)

val movieList = movieDTOLists.toMutableList().map { Movie.toMovie(it) }

val searchMovieDTOLists = listOf(
    MovieDTO(
        id = "1",
        title = "search result 1",
        releaseDate = "2012",
        description = "hello movie",
        posterPath = "url1",
        backdropPath = "url11"
    ),
    MovieDTO(
        id = "2",
        title = "search result 2",
        releaseDate = "2013",
        description = "world movie",
        posterPath = "url2",
        backdropPath = "url22"
    )
)

val searchMovieList = searchMovieDTOLists.toMutableList().map { Movie.toMovie(it) }
