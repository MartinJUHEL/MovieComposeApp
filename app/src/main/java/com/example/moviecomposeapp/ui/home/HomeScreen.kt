package com.example.moviecomposeapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.ui.component.ErrorView
import com.example.moviecomposeapp.ui.home.component.TrendingMoviesComponent
import com.example.moviecomposeapp.ui.home.component.popularMoviesComponent

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val homeViewState by viewModel.homeViewStateLiveData.observeAsState(initial = HomeViewState.Loading)

    // LaunchedEffect(Unit) {
    //      viewModel.getMovies()
    //   }
    if (homeViewState is HomeViewState.Error) {
        ErrorScreen(viewModel = viewModel)
    } else {
        LazyColumn {
            item {
                Text(
                    stringResource(R.string.trending_movies_title),
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
                )
            }
            item {
                TrendingMoviesComponent(
                    homeViewState = homeViewState ,
                    navController = navController
                )
            }
            item {
                Text(
                    stringResource(R.string.popular_movies_title),
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
                )
            }
            popularMoviesComponent(homeViewState = homeViewState, navController = navController)
        }
    }

}

@Composable
private fun ErrorScreen(viewModel: HomeViewModel) {
    ErrorView(retryAvailable = true, onRetry = { viewModel.getMovies() })
}

