package com.example.githubapp.features.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.components.TextIfNotEmpty
import com.example.githubapp.components.TextWithIcon
import com.example.githubapp.data.model.Repository

@Composable
fun RepositoryCard(repository: Repository) {
    OutlinedCard(modifier = Modifier.fillMaxWidth().testTag(repository.name)) {
        Column(Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "Repository")
                Spacer(Modifier.width(6.dp))
                Text(text = repository.name, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.width(6.dp))
            }

            TextIfNotEmpty(
                modifier = Modifier,
                text = repository.description,
                textAlign = TextAlign.Start
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(Color.LightGray)
                        .clip(CircleShape)
                )

                Text(text = repository.language)

                Spacer(Modifier.width(6.dp))

                TextWithIcon(icon = Icons.Filled.Star, text = "${repository.stars}")

                Spacer(Modifier.width(6.dp))

                TextIfNotEmpty(modifier = Modifier, text = repository.visibility)
            }
        }
    }
}

@Preview
@Composable
fun RepositoryCardPreview() {
    RepositoryCard(repository = Repository("name", "visibility", "description", "url", "Java", 5))
}