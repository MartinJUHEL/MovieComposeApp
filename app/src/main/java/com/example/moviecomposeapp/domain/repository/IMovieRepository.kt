package com.example.moviecomposeapp.domain.repository

import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.domain.model.YoutubeVideo
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun fetchPopularMovies(): Flow<List<Movie>>
    suspend fun fetchMovie(movieId: Int): Flow<Movie>

    suspend fun fetchVideo(movieId: Int): Flow<YoutubeVideo?>

    suspend fun fetchTrendingMovie(): Flow<List<Movie>>
}