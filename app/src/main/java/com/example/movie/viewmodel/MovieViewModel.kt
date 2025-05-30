package com.example.movie.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movie.data.MovieRepository
import com.example.movie.model.Movie
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {
    val topMovies: StateFlow<List<Movie>> = repo.getTopMovies()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val latestMovies: StateFlow<List<Movie>> = repo.getLatestMovies()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun refreshAll() {
        viewModelScope.launch {
            repo.refreshTopMovies()
            repo.refreshLatestMovies()
        }
    }
}

class MovieViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repo = MovieRepository(context)
        return MovieViewModel(repo) as T
    }
}
