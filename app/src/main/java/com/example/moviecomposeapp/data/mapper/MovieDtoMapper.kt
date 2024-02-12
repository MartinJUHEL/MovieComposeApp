package com.example.moviecomposeapp.data.mapper

import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.data.dto.MovieDto
import com.example.moviecomposeapp.domain.usecase.GetGenreFromIdUseCase
import com.example.moviecomposeapp.domain.usecase.GetImagePathUseCase
import javax.inject.Inject

class MovieDtoMapper @Inject constructor(
    private val getImagePathUseCase: GetImagePathUseCase,
    private val getGenreFromIdUseCase: GetGenreFromIdUseCase
) {

    fun execute(movieDto: MovieDto): Movie =
        movieDto.toMovie()

    private fun MovieDto.toMovie(): Movie = Movie(
        id = id,
        overview = overview,
        posterPath = getImagePathUseCase.execute(poster_path),
        title = title,
        voteAverage = vote_average,
        voteCount = vote_count,
        video = video,
        adult = adult,
        backdropPath = backdrop_path,
        popularity = popularity,
        genres = genres ?: getGenreFromIdUseCase.execute(genre_ids),
        originalLanguage = original_language,
        originalTitle = original_title,
        releaseDate = release_date,
        homePage = homepage
    )
}
