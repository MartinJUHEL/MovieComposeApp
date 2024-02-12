package com.example.moviecomposeapp.domain.usecase.home

import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mock.MoviesMock
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class GetTrendingMoviesUseCaseTest {

    @Mock
    private lateinit var movieRepository: IMovieRepository

    private lateinit var sut: GetTrendingMoviesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = GetTrendingMoviesUseCase(movieRepository)
    }

    @Test
    fun getTrendingMoviesUseCaseTest() = runTest {
        movieRepository.stub {
            onBlocking {
                fetchTrendingMovie()
            } doReturn (flowOf(MoviesMock().moviesMock()))
        }
        val expectedResult = MoviesMock().moviesMock()
        sut.execute().collect { result: List<Movie> ->
            assertEquals(result, expectedResult)
        }
    }
}