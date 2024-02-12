package com.example.moviecomposeapp.domain.usecase.detail

import com.example.moviecomposeapp.domain.model.YoutubeVideo
import com.example.moviecomposeapp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetYoutubeVideoUseCase @Inject constructor(
    private val movieRepository: IMovieRepository
) {
    suspend fun execute(movieId: Int): Flow<YoutubeVideo?> = movieRepository.fetchVideo(movieId)
}