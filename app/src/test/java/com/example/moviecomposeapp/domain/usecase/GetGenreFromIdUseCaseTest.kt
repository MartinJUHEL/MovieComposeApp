package com.example.moviecomposeapp.domain.usecase

import com.example.moviecomposeapp.domain.model.Genre
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetGenreFromIdUseCaseTest {

    private val sut: GetGenreFromIdUseCase = GetGenreFromIdUseCase()

    @Test
    fun getGenreFromIdUseCaseTestNull() {
        val expectedResult = listOf<Genre>()
        val result = sut.execute(null)
        assertEquals(expectedResult, result)
    }

    @Test
    fun getGenreFromIdUseCaseTestWrongId() {
        val genreId = 1
        val expectedResult = listOf(Genre(genreId, ""))
        val result = sut.execute(listOf(genreId))
        assertEquals(expectedResult, result)
    }

    @Test
    fun getGenreFromIdUseCaseTest() {
        val actionId = 28
        val horrorId = 27
        val expectedResult = listOf(
            Genre(actionId, "Action"), Genre(horrorId, "Horror")
        )
        val result = sut.execute(listOf(actionId, horrorId))
        assertEquals(expectedResult, result)
    }
}