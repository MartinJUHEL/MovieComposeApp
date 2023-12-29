package com.example.moviecomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviecomposeapp.ui.detail.MovieDetailsScreen
import com.example.moviecomposeapp.ui.home.HomeScreen

enum class AppScreen {
    HOME,
    MOVIE_DETAILS,
}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(AppScreen.HOME.name)
    object MovieDetails : NavigationItem(AppScreen.MOVIE_DETAILS.name)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = NavigationItem.MovieDetails.route + "/{movieId}", arguments = listOf(
            navArgument("movieId") {
                type = NavType.IntType
                nullable = false
            }
        )) {
            MovieDetailsScreen(navController = navController)
        }
    }
}