package com.example.moviecomposeapp.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeapp.home.presentation.viewstate.PopularMovieViewState
import com.example.moviecomposeapp.home.domain.usecase.GetPopularMovieViewStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMovieViewStateUseCase: GetPopularMovieViewStateUseCase
) : ViewModel() {

    private val _popularMoviesViewStateLiveDate = MutableLiveData<PopularMovieViewState>()
    val popularMoviesViewStateLiveDate: LiveData<PopularMovieViewState> =
        _popularMoviesViewStateLiveDate

    init {
        getMovies()
    }

    fun getMovies() {
        _popularMoviesViewStateLiveDate.postValue(PopularMovieViewState.Loading)
        viewModelScope.launch {
            val popularMovieViewState: PopularMovieViewState =
                getPopularMovieViewStateUseCase.execute()
            _popularMoviesViewStateLiveDate.postValue(popularMovieViewState)
        }
    }

}