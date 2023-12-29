package com.example.moviecomposeapp.domain.usecase.home

import android.util.Log
import com.example.moviecomposeapp.ui.home.HomeViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHomeViewStateUseCase @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase
) {
    suspend fun execute(): Flow<HomeViewState> {
        return getPopularMoviesUseCase.execute()
            .combine(getTrendingMoviesUseCase.execute()) { popularMovies, trendingMovies ->
                HomeViewState.Data(popularMovies, trendingMovies)
            }.catch {
                Log.e(
                    "GetPopularMovieViewStateUseCase",
                    "ERROR : ${it.message} STACKTRACE : ${it.stackTrace}"
                )
                HomeViewState.Error
            }
    }
}