package com.example.moviecomposeapp.domain.usecase

import com.example.moviecomposeapp.core.api.movie.MovieApiConfig.Companion.IMAGE_BASE_URL
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetImagePathUseCaseTest {

    private val sut: GetImagePathUseCase = GetImagePathUseCase()

    @Test
    fun getImagePathUseCaseTest() {
        val imagePath = "path"
        val expectedResult = "${IMAGE_BASE_URL}path"
        val result = sut.execute(imagePath)
        assertEquals(expectedResult, result)
    }
}


