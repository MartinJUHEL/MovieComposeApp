package com.example.moviecomposeapp.home.presentation.viewstate

import com.example.moviecomposeapp.common.domain.models.Movie

sealed class PopularMovieViewState {
    object Loading : PopularMovieViewState()
    object Error : PopularMovieViewState()
    class Data(val movies: List<Movie>) : PopularMovieViewState()
}
