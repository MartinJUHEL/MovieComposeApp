package com.example.moviecomposeapp.data.dto

data class MovieResponseDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)