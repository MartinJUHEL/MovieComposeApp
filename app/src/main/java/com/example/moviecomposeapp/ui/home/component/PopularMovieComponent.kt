package com.example.moviecomposeapp.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.domain.usecase.DisplayCategoriesUseCase
import com.example.moviecomposeapp.navigation.NavigationItem
import com.example.moviecomposeapp.ui.home.HomeViewState

fun LazyListScope.popularMoviesComponent(homeViewState: HomeViewState, navController: NavController) {
    if (homeViewState is HomeViewState.Loading) {
        popularMovieShimmer()
    } else if (homeViewState is HomeViewState.Data) {
        popularMovieList(
            popularMovies = homeViewState.popularMovies,
            navController = navController
        )
    }
}

private fun LazyListScope.popularMovieShimmer() {
        items(6) {
            MovieCardShimmer()
    }
}

private fun LazyListScope.popularMovieList(
    popularMovies: List<Movie>,
    navController: NavController
) {
    item {
        Box(modifier = Modifier.width(5.dp))
    }
    items(popularMovies) { movie ->
        MovieCard(
            title = movie.title,
            categories = DisplayCategoriesUseCase().execute(movie.genres),
            posterPath = movie.posterPath,
            onClick = {
                navController.navigate(NavigationItem.MovieDetails.withArgs(movie.id.toString()))
            })
    }
    item {
        Box(modifier = Modifier.width(5.dp))
    }
}