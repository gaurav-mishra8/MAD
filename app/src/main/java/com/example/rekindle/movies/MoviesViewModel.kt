package com.example.rekindle.movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rekindle.Result
import com.example.rekindle.asResult
import com.example.rekindle.movies.data.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        val query = savedStateHandle.get<String?>("query")
        query?.let {
            searchMovies(query)
        }
    }

    private val searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<MoviesState> = searchQuery.debounce(200)
        .filter { it.length > 2 }
        .flatMapLatest { query ->
            moviesRepo.searchMovie(query)
        }.map { result ->
            when (result) {
                is Result.Success -> {
                    MoviesState(
                        movies = result.data,
                        isLoading = false,
                        error = null,
                    )
                }
                is Result.Loading -> {
                    MoviesState(
                        movies = emptyList(),
                        isLoading = true,
                        error = null,
                    )
                }
                is Result.Error -> {
                    MoviesState(
                        movies = emptyList(),
                        isLoading = false,
                        error = result.exception?.message ?: "Something went wrong",
                    )
                }
            }
        }.stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            MoviesState()
        )

    fun searchMovies(query: String?) {
        query?.let {
            searchQuery.value = it
            savedStateHandle["query"] = it
        }
    }
}