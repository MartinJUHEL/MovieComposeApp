package com.example.moviecomposeapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.moviecomposeapp.R

@Composable
fun ErrorView(retryAvailable: Boolean, onRetry: (() -> Unit)?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        painterResource(R.drawable.baseline_error_outline_24)
        Text(stringResource(id = R.string.error_text))
        if (retryAvailable) {
            Button(onClick = { onRetry?.let { onRetry() } }) {
                Text(text = stringResource(id = R.string.refresh))
            }
        }
    }
}