package com.example.moviecomposeapp.core.api.movie

import com.example.moviecomposeapp.home.data.dto.MovieResponseDto
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(): MovieResponseDto
}