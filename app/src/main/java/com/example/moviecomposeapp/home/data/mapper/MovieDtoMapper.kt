package com.example.moviecomposeapp.home.data.mapper

import com.example.moviecomposeapp.common.domain.models.Movie
import com.example.moviecomposeapp.home.data.dto.MovieDto

fun MovieDto.toMovie(): Movie = Movie(
    id = id,
    overview = overview,
    posterPath = poster_path,
    title = title,
    voteAverage = vote_average,
    voteCount = vote_count,
    video = video,
    adult = adult,
    backdropPath = backdrop_path,
    popularity = popularity,
    genreIds = genre_ids,
    originalLanguage = original_language,
    originalTitle = original_title,
    releaseDate = release_date
)