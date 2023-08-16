package com.example.githubapp.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.githubapp.R

@Composable
fun ErrorLayout(@StringRes errorMessage: Int, onRetryButtonClicked: () -> Unit = {}) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = errorMessage))

        Button(onClick = { onRetryButtonClicked() }) {
            Text(text = stringResource(R.string.retry_button_text))
        }
    }
}
