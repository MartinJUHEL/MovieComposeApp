package com.example.moviecomposeapp.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalUriHandler
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecomposeapp.domain.usecase.home.GetHomeViewStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeViewStateUseCase: GetHomeViewStateUseCase,
) : ViewModel() {

    private val _homeViewStateLiveDate = MutableLiveData<HomeViewState>()
    val homeViewStateLiveData: LiveData<HomeViewState> =
        _homeViewStateLiveDate

    init {
        getMovies()
    }

    fun getMovies() {
        _homeViewStateLiveDate.postValue(HomeViewState.Loading)
        viewModelScope.launch {
            getHomeViewStateUseCase.execute().collect {
                _homeViewStateLiveDate.postValue(it)
            }
        }
    }
}