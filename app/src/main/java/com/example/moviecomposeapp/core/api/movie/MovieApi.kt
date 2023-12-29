package com.example.moviecomposeapp.core.api.movie

import com.example.moviecomposeapp.data.dto.MovieDto
import com.example.moviecomposeapp.data.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(): MovieResponseDto

    @GET("movie/{movie_id}")
    suspend fun fetchMovie(@Path("movie_id") id: Int): MovieDto

    @GET("trending/movie/week")
    suspend fun fetchTrendingMovies(): MovieResponseDto
}