package com.example.moviecomposeapp.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.ui.component.shimmerBrush
@Preview
@Composable
fun MovieCardShimmer() {
    Card(modifier = Modifier
        .heightIn(150.dp)
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
        }) {
        Row {
            showImageShimmer()
            Column {
                Text(
                    "              ", modifier = Modifier
                        .padding(start = 5.dp, top = 10.dp, bottom = 10.dp)
                        .background(
                            shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = true
                            )
                        )
                )
                Text(
                    "                        ", modifier = Modifier
                        .padding(start = 5.dp)
                        .background(
                            shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = true
                            )
                        )
                )
            }
        }
    }
}

@Composable
private fun showImageShimmer() {
    Box(
        modifier = Modifier.heightIn(200.dp).width(168.75.dp).background(
            shimmerBrush(
                targetValue = 1300f,
                showShimmer = true
            )
        )
    )
}

