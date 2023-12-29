package com.example.moviecomposeapp.domain.repository

import com.example.moviecomposeapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun fetchPopularMovies(): Flow<List<Movie>>
    suspend fun fetchMovie(movieId : Int): Flow<Movie>

    suspend fun fetchTrendingMovie(): Flow<List<Movie>>
}