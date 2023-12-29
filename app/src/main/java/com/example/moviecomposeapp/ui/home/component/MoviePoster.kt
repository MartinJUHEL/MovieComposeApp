package com.example.moviecomposeapp.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.ui.component.shimmerBrush

@Composable
fun MoviePoster(movie: Movie, onClick: (movieId: Int) -> Unit) {
    Card(
        modifier = Modifier
            .size(150.dp, 225.dp)
            .padding(5.dp)
            .clickable {
                onClick(movie.id)
            }
    ) {
        Box {
            val showImageShimmer = remember { mutableStateOf(true) }
            AsyncImage(
                model = movie.posterPath,
                contentDescription = stringResource(id = R.string.movie_poster),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .background(
                        shimmerBrush(
                            targetValue = 1300f,
                            showShimmer = showImageShimmer.value
                        )
                    )
                    .width(150.dp)
                    .heightIn(min = 220.dp),
                onSuccess = { showImageShimmer.value = false },
                onError = { showImageShimmer.value = false },
                error = painterResource(R.drawable.baseline_error_outline_24)
            )
        }
    }
}
