package com.example.moviecomposeapp.domain.usecase

import com.example.moviecomposeapp.core.api.movie.MovieApiConfig
import javax.inject.Inject

class GetImagePathUseCase @Inject constructor() {
    fun execute(imagePath: String): String {
        return "${MovieApiConfig.IMAGE_BASE_URL}$imagePath"
    }
}