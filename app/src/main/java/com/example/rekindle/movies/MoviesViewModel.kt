package com.example.rekindle.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rekindle.Result
import com.example.rekindle.asResult
import com.example.rekindle.movies.data.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesRepo: MoviesRepo
) : ViewModel() {

    val state: StateFlow<MoviesState> = moviesRepo.searchMovie()
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> {
                    MoviesState(movies = result.data, isLoading = false, error = null)
                }
                is Result.Loading -> {
                     MoviesState(movies = emptyList(), isLoading = true, error = null)
                }
                is Result.Error -> {
                    MoviesState(
                        movies = emptyList(),
                        isLoading = false,
                        error = result.exception?.message ?: "Something went wrong"
                    )
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            MoviesState()
        )
}