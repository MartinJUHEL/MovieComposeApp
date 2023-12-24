package com.example.moviecomposeapp.home.domain.usecase

import android.util.Log
import com.example.moviecomposeapp.common.domain.models.Movie
import com.example.moviecomposeapp.home.presentation.viewstate.PopularMovieViewState
import javax.inject.Inject

class GetPopularMovieViewStateUseCase @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) {

    suspend fun execute(): PopularMovieViewState {
        return try {
            val movies: List<Movie> = getPopularMoviesUseCase.execute()
            PopularMovieViewState.Data(movies)
        } catch (e: Exception) {
            Log.e("GetPopularMovieViewStateUseCase", e.message.toString())
            PopularMovieViewState.Error
        }
    }
}