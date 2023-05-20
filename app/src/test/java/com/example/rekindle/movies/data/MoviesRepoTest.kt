package com.example.rekindle.movies.data

import android.accounts.NetworkErrorException
import com.example.rekindle.Result
import com.example.rekindle.movies.data.db.LatestMoviesDao
import com.example.rekindle.movies.data.db.SearchResultDao
import com.example.model.dto.SearchMovieResponse
import com.example.rekindle.movies.movieDTOLists
import com.example.rekindle.movies.movieList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class MoviesRepoTest {

    private lateinit var moviesRepo: MoviesRepoImpl

    private val movieService: MoviesService = mock(MoviesService::class.java)
    private val searchResultDao = mock(SearchResultDao::class.java)
    private val latestMovieDao = mock(LatestMoviesDao::class.java)

    @Before
    fun setUp() {
        moviesRepo = MoviesRepoImpl(movieService, searchResultDao, latestMovieDao)
    }

    @Test
    fun verify_service_returns_loading() = runTest {
        whenever(movieService.searchMovies(anyString())).then {
            testSearchResponse
        }
        val movieResponse = moviesRepo.searchMovie("world").toList()
        assert(movieResponse[0] is com.example.rekindle.Result.Loading)
    }

    @Test
    fun when_service_returns_success_verify_repo_returns_success_result() = runTest {
        whenever(movieService.searchMovies(anyString())).then {
            testSearchResponse
        }

        val movieResponse = moviesRepo.searchMovie("world").toList()

        assert((movieResponse[1] as Result.Success).data == movieList)
    }

    @Test
    fun when_service_returns_error_verify_repo_returns_error_result() = runTest {
        whenever(movieService.searchMovies(anyString())).then {
            throw NetworkErrorException()
        }

        val movieResponse = moviesRepo.searchMovie("world").toList()

        assert(movieResponse[1] is Result.Error)
    }

    private val testSearchResponse = SearchMovieResponse(
        page = 1,
        results = movieDTOLists,
        totalPages = 5,
        totalResults = 12,
    )
}