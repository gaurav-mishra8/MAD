package com.example.rekindle.movies.movieslist

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.rekindle.movies.MainDispatcherRule
import com.example.rekindle.movies.TestMoviesRepo
import com.example.rekindle.movies.movieList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
internal class MoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testMoviesRepo = TestMoviesRepo()

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(testMoviesRepo, SavedStateHandle())
    }

    @Test
    fun `verify view model returns correct state when getLatestMovies is success`() = runTest {
        viewModel.uiState.test {
            val firstItem = awaitItem()
            assert(
                firstItem == MoviesState(
                    movieList = emptyList(),
                    isLoading = true,
                    error = null,
                    isSearchMode = false
                )
            )

            val secondItem = awaitItem()
            assert(
                secondItem == MoviesState(
                    movieList = movieList,
                    isLoading = false,
                    error = null,
                    isSearchMode = false
                )
            )
        }
    }

    @Test
    fun `verify view model returns correct state when getLatestMovies is failure`() = runTest {
        testMoviesRepo.returnError = true

        viewModel.uiState.test {
            val firstItem = awaitItem()
            assert(
                firstItem == MoviesState(
                    movieList = emptyList(),
                    isLoading = true,
                    error = null,
                    isSearchMode = false
                )
            )

            val secondItem = awaitItem()
            assert(
                secondItem == MoviesState(
                    movieList = emptyList(),
                    isLoading = false,
                    error = TestMoviesRepo.ERROR_MESSAGE,
                    isSearchMode = false
                )
            )
        }
    }
}