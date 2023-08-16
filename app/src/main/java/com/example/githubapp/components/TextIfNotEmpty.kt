package com.example.githubapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextIfNotEmpty(modifier: Modifier, text: String, textAlign: TextAlign = TextAlign.Center) {
    if (text.isNotEmpty()) {
        Text(modifier = modifier.fillMaxWidth(), text = text, textAlign = textAlign)
    }
}

@Preview
@Composable
fun TextIfNotEmptyPreview() {
    TextIfNotEmpty(
        modifier = Modifier,
        text = "Um texto bem grande aqui. Um texto bem grande aqui. Um texto bem grande aqui. Um texto bem grande aqui. Um texto bem grande aqui. Um texto bem grande aqui. "
    )
}