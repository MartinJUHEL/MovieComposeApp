package com.example.moviecomposeapp.home.domain.repository

import com.example.moviecomposeapp.common.domain.models.Movie

interface IMovieRepository {
    suspend fun fetchPopularMovies(): List<Movie>
}