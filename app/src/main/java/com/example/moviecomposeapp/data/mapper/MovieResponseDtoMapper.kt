package com.example.moviecomposeapp.data.mapper

import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.data.dto.MovieResponseDto
import javax.inject.Inject

class MovieResponseDtoMapper @Inject constructor(private val movieDtoMapper: MovieDtoMapper) {
    fun execute(response: MovieResponseDto): List<Movie> {
        return response.results.map { movieDtoMapper.execute(it) }
    }
}
