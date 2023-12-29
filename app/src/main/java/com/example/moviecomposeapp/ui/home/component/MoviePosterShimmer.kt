package com.example.moviecomposeapp.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.domain.model.Movie
import com.example.moviecomposeapp.ui.component.shimmerBrush

@Preview
@Composable
fun MoviePosterShimmer() {
    Card(
        modifier = Modifier
            .size(150.dp, 225.dp)
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    shimmerBrush(
                        targetValue = 1300f,
                        showShimmer = true
                    )
                )
        )
    }
}
