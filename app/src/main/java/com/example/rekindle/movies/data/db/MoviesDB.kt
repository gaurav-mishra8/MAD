package com.example.rekindle.movies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rekindle.movies.model.Movie
import com.example.rekindle.movies.model.SearchQuery

@Database(
    entities = [SearchQuery::class, Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun searchResultDao(): SearchResultDao
    abstract fun latestMoviesDao(): LatestMoviesDao
}