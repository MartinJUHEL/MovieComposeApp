package com.example.moviecomposeapp.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecomposeapp.common.domain.models.Movie
import com.example.moviecomposeapp.home.presentation.viewstate.PopularMovieViewState

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val popularMovieViewState by viewModel.popularMoviesViewStateLiveDate.observeAsState(initial = PopularMovieViewState.Loading)

    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }

    Column {
        when (popularMovieViewState) {
            is PopularMovieViewState.Data -> MovieList((popularMovieViewState as PopularMovieViewState.Data).movies)
            PopularMovieViewState.Error -> ErrorScreen()
            PopularMovieViewState.Loading -> Loader()
        }
    }
}

@Composable
private fun ErrorScreen() {
    Text(
        text = "Error",
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun Loader() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
private fun MovieList(popularMovies: List<Movie>) {
    if (popularMovies.isEmpty()) {
        Text(text = "Nothing to show")
    } else {
        // Display the list of credit cards
        LazyColumn {
            items(popularMovies) { movie ->
                Text(text = movie.title)
                Divider() // Add a divider between items
            }
        }
    }
}