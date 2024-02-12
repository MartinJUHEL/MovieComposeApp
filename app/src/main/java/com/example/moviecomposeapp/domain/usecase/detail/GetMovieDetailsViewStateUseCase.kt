package com.example.moviecomposeapp.domain.usecase.detail

import android.util.Log
import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.ui.detail.MovieDetailViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetMovieDetailsViewStateUseCase @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getYoutubeVideoUseCase: GetYoutubeVideoUseCase
) {
    suspend fun execute(movieId: Int): Flow<MovieDetailViewState> {
        return getMovieUseCase.execute(movieId)
            .zip(getYoutubeVideoUseCase.execute(movieId)) { movie, youtubeVideo ->
                MovieDetailViewState.Data(movie, youtubeVideo)
            }.catch {
            Log.e(
                "GetMovieDetailsViewStateUseCase",
                "ERROR : ${it.message} STACKTRACE : ${it.stackTrace}"
            )
            MovieDetailViewState.Error
        }
    }
}