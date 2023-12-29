package com.example.moviecomposeapp.ui.home

import com.example.moviecomposeapp.domain.model.Movie

sealed class HomeViewState {
    object Loading : HomeViewState()
    object Error : HomeViewState()
    class Data(val trendingMovies: List<Movie>, val popularMovies: List<Movie>) : HomeViewState()
}
