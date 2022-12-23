package com.example.rekindle.movies.data.db

import androidx.room.*
import com.example.rekindle.movies.model.Movie
import com.example.rekindle.movies.model.SearchQuery


@Dao
interface SearchResultDao {

    @Query("SELECT * FROM SearchQuery")
    suspend fun getRecentSearches(): List<SearchQuery>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSearchResult(result: SearchQuery)
}

@Dao
interface LatestMoviesDao {
    @Query("SELECT * FROM Movie")
    suspend fun getLatestMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLatestMovies(movieDTOS: List<Movie>)
}
