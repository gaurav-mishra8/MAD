package com.example.rekindle.di

import android.app.Application
import androidx.room.Room
import com.example.rekindle.movies.data.db.LatestMoviesDao
import com.example.rekindle.movies.data.db.MoviesDatabase
import com.example.rekindle.movies.data.db.SearchResultDao
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
    fun provideRoomDatabase(appContext: Application): MoviesDatabase {
        return Room.databaseBuilder(
            appContext,
            MoviesDatabase::class.java, "movies-db"
        ).build()
    }

    @Provides
    @Singleton
    fun getSearchResultDao(db: MoviesDatabase): SearchResultDao {
        return db.searchResultDao()
    }

    @Provides
    @Singleton
    fun getLatestMoviesDao(db: MoviesDatabase): LatestMoviesDao {
        return db.latestMoviesDao()
    }
}