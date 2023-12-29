package com.example.moviecomposeapp.ui.detail

import com.example.moviecomposeapp.domain.model.Movie

sealed class MovieDetailViewState {
    object Loading : MovieDetailViewState()
    object Error : MovieDetailViewState()
    class Data(val movie: Movie) : MovieDetailViewState()
}
