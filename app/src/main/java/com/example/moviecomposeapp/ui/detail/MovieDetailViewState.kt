package com.example.moviecomposeapp.ui.detail

import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.domain.model.YoutubeVideo

sealed class MovieDetailViewState {
    object Loading : MovieDetailViewState()
    object Error : MovieDetailViewState()
    class Data(val movie: Movie, val youtubeVideo : YoutubeVideo?) : MovieDetailViewState()
}
