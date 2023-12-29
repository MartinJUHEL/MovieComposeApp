package com.example.moviecomposeapp.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.ui.component.ErrorView
import com.example.moviecomposeapp.ui.detail.component.DetailsTopBar

@Composable
fun MovieDetailsScreen(navController: NavController) {
    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val movieDetailwViewState by viewModel.movieDetailsViewStateLiveData.observeAsState(initial = MovieDetailViewState.Loading)


    Box {
        when (movieDetailwViewState) {
            is MovieDetailViewState.Data -> Details(
                (movieDetailwViewState as MovieDetailViewState.Data).movie,
                navController
            )

            MovieDetailViewState.Error -> ErrorScreen(viewModel)
            MovieDetailViewState.Loading -> Loader()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Details(movie: Movie, navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DetailsTopBar(
                scrollBehavior = scrollBehavior,
                title = movie.title,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(movie.title)
                Text(movie.overview)
            }
        }
    }
}

@Composable
private fun ErrorScreen(viewModel: MovieDetailsViewModel) {
    ErrorView(retryAvailable = true, onRetry = { viewModel.getMovie() })
}

@Composable
private fun Loader() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
    )
}