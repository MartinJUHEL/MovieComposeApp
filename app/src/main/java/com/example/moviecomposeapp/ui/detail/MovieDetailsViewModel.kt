package com.example.moviecomposeapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeapp.domain.usecase.detail.GetMovieDetailsViewStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getMovieDetailsViewStateUseCase: GetMovieDetailsViewStateUseCase
) :
    ViewModel() {
    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _movieDetailsviewStateLiveData = MutableLiveData<MovieDetailViewState>()
    val movieDetailsViewStateLiveData: LiveData<MovieDetailViewState> =
        _movieDetailsviewStateLiveData

    init {
        getMovie()
    }

    fun getMovie() {
        _movieDetailsviewStateLiveData.postValue(MovieDetailViewState.Loading)
        viewModelScope.launch {
            getMovieDetailsViewStateUseCase.execute(movieId).collect {
                _movieDetailsviewStateLiveData.postValue(it)
            }
        }
    }

}