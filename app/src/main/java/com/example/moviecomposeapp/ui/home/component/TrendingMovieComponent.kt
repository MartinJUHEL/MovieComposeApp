package com.example.moviecomposeapp.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.navigation.NavigationItem
import com.example.moviecomposeapp.ui.home.HomeViewState

@Composable
fun TrendingMoviesComponent(homeViewState: HomeViewState, navController: NavController) {
    if (homeViewState is HomeViewState.Loading) {
        TrendingMovieShimmer()
    } else if (homeViewState is HomeViewState.Data) {
        TrendingMovieList(
            trendingMovies = homeViewState.trendingMovies,
            navController = navController
        )
    }
}

@Composable
private fun TrendingMovieShimmer() {
    LazyRow {
        items(6) {
            MoviePosterShimmer()
        }
    }
}

@Composable
private fun TrendingMovieList(trendingMovies: List<Movie>, navController: NavController) {
    if (trendingMovies.isEmpty()) {
        Text(text = "Nothing to show")
    } else {
        // Display the list of credit cards
        LazyRow {
            item {
                Box(modifier = Modifier.width(5.dp))
            }
            items(trendingMovies) { movie ->
                MoviePoster(movie = movie, onClick = {
                    navController.navigate(NavigationItem.MovieDetails.withArgs(it.toString()))
                })
            }
            item {
                Box(modifier = Modifier.width(5.dp))
            }
        }
    }
}