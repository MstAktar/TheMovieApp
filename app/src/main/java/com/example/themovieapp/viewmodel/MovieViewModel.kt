package com.example.themovieapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.themovieapp.model.Movie
import com.example.themovieapp.model.MovieApi

sealed interface MovieUIState{
    data class Success(val movies: Movie) : MovieUIState
    object Error: MovieUIState
    object Loading: MovieUIState
}

class MovieViewModel: ViewModel(){
    var movieUIState: MovieUIState by mutableStateOf<MovieUIState>(MovieUIState.Loading)
        private set

    init {
        getMovies()
    }

    private fun getMovies(){
        viewModelScope.launch {
            var movieApi: MovieApi? = null
            try {
                movieApi = MovieApi!!.getInstance()
                movieUIState = MovieUIState.Loading
                movieUIState = MovieUIState.Success(movieApi.getMovies())
            } catch (e: Exception){
                Log.d("MOVIE VIEW MODEL", e.message.toString())
                movieUIState = MovieUIState.Error
            }
        }
    }
}
