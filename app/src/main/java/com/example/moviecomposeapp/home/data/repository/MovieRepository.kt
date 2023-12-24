package com.example.moviecomposeapp.home.data.repository

import com.example.moviecomposeapp.core.api.movie.MovieApi
import com.example.moviecomposeapp.common.domain.models.Movie
import com.example.moviecomposeapp.home.data.mapper.toMovie
import com.example.moviecomposeapp.home.data.mapper.toMovies
import com.example.moviecomposeapp.home.domain.repository.IMovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi) : IMovieRepository {
    override suspend fun fetchPopularMovies(): List<Movie> {
        return movieApi.fetchPopularMovies().toMovies()
    }
}