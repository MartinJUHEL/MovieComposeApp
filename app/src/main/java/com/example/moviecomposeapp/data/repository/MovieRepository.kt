package com.example.moviecomposeapp.data.repository

import com.example.moviecomposeapp.core.api.movie.MovieApi
import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.data.mapper.MovieDtoMapper
import com.example.moviecomposeapp.data.mapper.MovieResponseDtoMapper
import com.example.moviecomposeapp.domain.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieResponseDtoMapper: MovieResponseDtoMapper,
    private val movieDtoMapper: MovieDtoMapper
) : IMovieRepository {
    override suspend fun fetchPopularMovies(): Flow<List<Movie>> {
        return flow {
            emit(movieResponseDtoMapper.execute(movieApi.fetchPopularMovies()))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchMovie(movieId: Int): Flow<Movie> {
        return flow {
            emit(movieDtoMapper.execute(movieApi.fetchMovie(movieId)))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchTrendingMovie(): Flow<List<Movie>> {
        return flow {
            emit(movieResponseDtoMapper.execute(movieApi.fetchTrendingMovies()))
        }.flowOn(Dispatchers.IO)
    }
}