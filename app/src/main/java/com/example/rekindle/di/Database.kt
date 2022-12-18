package com.example.rekindle.di

import android.app.Application
import androidx.room.Room
import com.example.rekindle.movies.model.SearchResultDao
import com.example.rekindle.movies.model.SearchResultsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Database {

    @Provides
    @Singleton
    fun getSearchResultDao(appContext: Application): SearchResultDao {
        val db = Room.databaseBuilder(
            appContext,
            SearchResultsDatabase::class.java, "search-result"
        ).build()

        return db.searchResultDao()
    }
}