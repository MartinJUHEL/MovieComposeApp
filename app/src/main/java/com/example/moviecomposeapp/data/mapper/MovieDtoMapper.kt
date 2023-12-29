package com.example.moviecomposeapp.data.mapper

import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.data.dto.MovieDto
import com.example.moviecomposeapp.domain.usecase.GetImagePathUseCase
import javax.inject.Inject

class MovieDtoMapper @Inject constructor(private val getImagePathUseCase: GetImagePathUseCase) {

    fun execute(movieDto: MovieDto): Movie =
        movieDto.toMovie(getImagePathUseCase.execute(movieDto.poster_path))

    private fun MovieDto.toMovie(imagePath: String): Movie = Movie(
        id = id,
        overview = overview,
        posterPath = imagePath,
        title = title,
        voteAverage = vote_average,
        voteCount = vote_count,
        video = video,
        adult = adult,
        backdropPath = backdrop_path,
        popularity = popularity,
        //todo
        genres = listOf(),
        originalLanguage = original_language,
        originalTitle = original_title,
        releaseDate = release_date
    )
}
