package com.example.rekindle.movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rekindle.Result
import com.example.rekindle.movies.data.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    init {
        val query = savedStateHandle.get<String?>("query")
        query?.let {
            searchMovies(query)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val state: StateFlow<MoviesState> = searchQuery.debounce(200)
        .flatMapLatest { query ->
            if (query.length < 3) {
                flowOf(Result.Success(emptyList()))
            } else {
                moviesRepo.searchMovie(query)
            }
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
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MoviesState()
        )

    fun searchMovies(query: String?) {
        query?.let {
            searchQuery.value = it
            savedStateHandle["query"] = it
        }
    }
}