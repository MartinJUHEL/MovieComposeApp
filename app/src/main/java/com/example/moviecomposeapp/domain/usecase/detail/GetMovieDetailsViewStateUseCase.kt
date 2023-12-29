package com.example.moviecomposeapp.domain.usecase.detail

import android.util.Log
import com.example.moviecomposeapp.ui.detail.MovieDetailViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieDetailsViewStateUseCase @Inject constructor(private val getMovieUseCase: GetMovieUseCase) {

    suspend fun execute(movieId: Int): Flow<MovieDetailViewState> {
        return getMovieUseCase.execute(movieId).catch {
            Log.e(
                "GetMovieDetailsViewStateUseCase",
                "ERROR : ${it.message} STACKTRACE : ${it.stackTrace}"
            )
            MovieDetailViewState.Error
        }.map {
            MovieDetailViewState.Data(it)
        }
    }
}