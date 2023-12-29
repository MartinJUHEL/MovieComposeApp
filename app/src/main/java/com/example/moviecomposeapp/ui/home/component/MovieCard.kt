package com.example.moviecomposeapp.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
import com.example.moviecomposeapp.ui.component.shimmerBrush

@Composable
fun MovieCard(
    title: String,
    categories: String,
    posterPath: String,
    onClick: () -> Unit,
) {
    Card(modifier = Modifier
        .height(200.dp)
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            onClick()
        }) {
        Row {
            showImage(posterPath = posterPath)
            Column {
                Text(
                    title, modifier = Modifier
                        .padding(start = 5.dp, top = 10.dp, bottom = 10.dp)
                )
                Text(
                    categories, modifier = Modifier
                        .padding(start = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun showImage(posterPath: String) {
    val showImageShimmer = remember { mutableStateOf(true) }
    AsyncImage(
        model = posterPath,
        contentDescription = stringResource(id = R.string.movie_poster),
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .background(
                shimmerBrush(
                    targetValue = 1300f,
                    showShimmer = showImageShimmer.value
                )
            )
            .height(200.dp),
        onSuccess = { showImageShimmer.value = false },
        onError = { showImageShimmer.value = false },
        error = painterResource(R.drawable.baseline_error_outline_24)
    )
}