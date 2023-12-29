package com.example.moviecomposeapp.domain.usecase.home

import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: IMovieRepository
) {
    suspend fun execute() : Flow<List<Movie>> = movieRepository.fetchPopularMovies()
}