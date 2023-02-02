package com.example.rekindle.movies.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rekindle.movie_id
import com.example.rekindle.movies.data.MoviesRepo
import com.example.rekindle.movies.data.MoviesRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    movieRepo: MoviesRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId = savedStateHandle.get<String>(movie_id)!!
    val state: StateFlow<MovieDetailState>

    init {
        state = movieRepo.getMovieById(movieId)
            .map { result ->
                when (result) {
                    is com.example.rekindle.Result.Loading -> {
                        MovieDetailState(
                            isLoading = true
                        )
                    }
                    is com.example.rekindle.Result.Success -> {
                        MovieDetailState(
                            isLoading = false,
                            movieDetail = result.data
                        )
                    }
                    is com.example.rekindle.Result.Error -> {
                        MovieDetailState(
                            error = result.exception?.message ?: "Something went wrong"
                        )
                    }
                }
            }.stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = MovieDetailState()
            )
    }
}