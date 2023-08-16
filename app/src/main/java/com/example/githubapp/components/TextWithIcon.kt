package com.example.githubapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextWithIcon(icon: ImageVector, text: String) {
    if(text.isNotEmpty()){
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(imageVector = icon, contentDescription = text)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = text.trim(), textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun TextWithIconPreview() {
    TextWithIcon(icon = Icons.Filled.Star, text = "MMy Nice Text more bugger My Nice Text more bugger y Nice Text more bugger. My Nice Text more bugger. My Nice Text more bugger.My Nice Text more bugger")
}