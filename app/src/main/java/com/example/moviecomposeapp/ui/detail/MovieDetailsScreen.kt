package com.example.moviecomposeapp.ui.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.domain.model.YoutubeVideo
import com.example.moviecomposeapp.domain.usecase.DisplayCategoriesUseCase
import com.example.moviecomposeapp.ui.component.ErrorView
import com.example.moviecomposeapp.ui.component.YouTubePlayer
import com.example.moviecomposeapp.ui.component.shimmerBrush

@Composable
fun MovieDetailsScreen(navController: NavController) {
    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val movieDetailwViewState by viewModel.movieDetailsViewStateLiveData.observeAsState(initial = MovieDetailViewState.Loading)


    Box {
        when (movieDetailwViewState) {
            is MovieDetailViewState.Data -> Details(
                (movieDetailwViewState as MovieDetailViewState.Data).movie,
                (movieDetailwViewState as MovieDetailViewState.Data).youtubeVideo,
                navController, viewModel
            )

            MovieDetailViewState.Error -> ErrorScreen(viewModel)
            MovieDetailViewState.Loading -> Loader()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Details(
    movie: Movie,
    youtubeVideo: YoutubeVideo?,
    navController: NavController,
    viewModel: MovieDetailsViewModel
) {
    val context = LocalContext.current

    Scaffold(
    ) { innerPadding ->
        Box()
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Black, Color.Transparent),
                            startY = 0f,
                            endY = size.height / 5
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    }
            ) {
                item {
                    Poster(posterPath = movie.posterPath)

                }
                item {
                    Row {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                movie.title,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(
                                DisplayCategoriesUseCase().execute(movie.genres),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .align(alignment = Alignment.Bottom)
                        ) {
                            Text(
                                "${movie.voteAverage} / 10",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Divider()
                    Text(
                        movie.overview,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(10.dp)
                    )
                    WatchTrailer(youtubeVideo?.youtubeId)
                    Box(modifier = Modifier.height(20.dp))
                    MovieUrlButton(url = movie.homePage, viewModel, context)
                    Box(modifier = Modifier.height(20.dp))
                }
            }
            IconButton(
                onClick = {
                    navController.popBackStack()
                }, modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_description)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieUrlButton(url: String?, viewModel: MovieDetailsViewModel, context: Context) {
    if (!url.isNullOrEmpty()) {
        Surface(onClick = {
            viewModel.openHomePage(url, context)
        }, modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    painterResource(id = R.drawable.baseline_language_24),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(id = R.string.movie_url_button_description),
                )

                Text(
                    url,
                    modifier = Modifier.padding(start = 5.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WatchTrailer(youtubeVideoId: String?) {
    if (!youtubeVideoId.isNullOrEmpty()) {
        Box(modifier = Modifier.height(20.dp))
        YouTubePlayer(youtubeVideoId = youtubeVideoId, lifecycleOwner = LocalLifecycleOwner.current)
    }
}

@Composable
private fun Poster(posterPath: String) {
    Box {
        val showImageShimmer = remember { mutableStateOf(true) }
        AsyncImage(
            model = posterPath,
            contentDescription = stringResource(id = R.string.movie_poster),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .background(
                    shimmerBrush(
                        targetValue = 1300f,
                        showShimmer = showImageShimmer.value
                    )
                )
                .fillMaxWidth(),
            onSuccess = { showImageShimmer.value = false },
            onError = { showImageShimmer.value = false },
            error = painterResource(R.drawable.baseline_error_outline_24)
        )
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