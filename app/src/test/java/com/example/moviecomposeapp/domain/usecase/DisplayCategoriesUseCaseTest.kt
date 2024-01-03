package com.example.moviecomposeapp.domain.usecase

import com.example.moviecomposeapp.domain.model.Genre
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DisplayCategoriesUseCaseTest {

    private val sut: DisplayCategoriesUseCase = DisplayCategoriesUseCase()

    @Test
    fun displayCategoriesUseCaseTestEmpty() {
        val genres = listOf<Genre>()
        val expectedResult = ""
        val result = sut.execute(genres)
        assertEquals(expectedResult, result)
    }

    @Test
    fun displayCategoriesUseCaseTest() {
        val genres = listOf(Genre(12, "Action"), Genre(29, "Horror"))
        val expectedResult = "Action, Horror"
        val result = sut.execute(genres)
        assertEquals(expectedResult, result)
    }
}