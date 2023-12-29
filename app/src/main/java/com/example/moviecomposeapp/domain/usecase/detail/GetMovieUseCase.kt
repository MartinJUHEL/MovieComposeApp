package com.example.moviecomposeapp.domain.usecase.detail

import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val movieRepository: IMovieRepository
) {
    suspend fun execute(movieId: Int): Flow<Movie> = movieRepository.fetchMovie(movieId)
}