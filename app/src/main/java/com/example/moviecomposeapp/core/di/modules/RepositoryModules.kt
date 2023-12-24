package com.example.moviecomposeapp.core.di.modules

import com.example.moviecomposeapp.home.data.repository.MovieRepository
import com.example.moviecomposeapp.home.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class) // or whatever graph fits your need the best
interface RepositoryModules {
    @Binds
    fun provideMainRepositoryImpl(repository: MovieRepository): IMovieRepository
}
