package com.example.rekindle.movies.model

import androidx.room.*

@Entity
data class SearchResult(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "query") val query: String,
/*
    @ColumnInfo(name = "results") val result: List<Movie>?
*/
)

@Dao
interface SearchResultDao {

    @Query("SELECT * FROM searchResult")
    suspend fun getRecentSearches(): List<SearchResult>

    @Insert
    suspend fun saveSearchResult(result: SearchResult)
}

@Database(entities = [SearchResult::class], version = 1, exportSchema = false)
abstract class SearchResultsDatabase : RoomDatabase() {
    abstract fun searchResultDao(): SearchResultDao
}
