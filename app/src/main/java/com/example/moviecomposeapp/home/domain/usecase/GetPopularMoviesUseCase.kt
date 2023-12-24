package com.example.moviecomposeapp.home.domain.usecase

import com.example.moviecomposeapp.common.domain.models.Movie
import com.example.moviecomposeapp.home.domain.repository.IMovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: IMovieRepository
) {
    suspend fun execute() : List<Movie> = movieRepository.fetchPopularMovies()
}