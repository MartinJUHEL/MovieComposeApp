package com.example.moviecomposeapp.home.data.mapper

import com.example.moviecomposeapp.common.domain.models.Movie
import com.example.moviecomposeapp.home.data.dto.MovieResponseDto

fun MovieResponseDto.toMovies(): List<Movie> {
    return this.results.map { it.toMovie() }
}