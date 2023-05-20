package com.example.rekindle.movies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.dto.MovieDTO

@Entity
data class Movie(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "posterUrl") val posterUrl: String,
) {

    companion object {
        fun toMovie(movieDTO: MovieDTO): Movie {
            return Movie(
                id = movieDTO.id,
                title = movieDTO.title,
                releaseDate = movieDTO.releaseDate ?: "",
                description = movieDTO.description,
                posterUrl = movieDTO.getPosterUrl,
            )
        }
    }

}
