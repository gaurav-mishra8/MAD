package com.example.rekindle.di

import com.example.rekindle.movies.data.MoviesRepo
import com.example.rekindle.movies.data.MoviesRepoImpl
import com.example.rekindle.movies.data.MoviesService
import com.example.rekindle.movies.data.db.LatestMoviesDao
import com.example.rekindle.movies.data.db.SearchResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Repository {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesService: MoviesService,
        searchResultDao: SearchResultDao,
        latestMoviesDao: LatestMoviesDao
    ): MoviesRepo {
        return MoviesRepoImpl(
            moviesService, searchResultDao, latestMoviesDao
        )
    }
}