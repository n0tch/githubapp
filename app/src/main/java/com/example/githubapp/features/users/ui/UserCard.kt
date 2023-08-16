package com.example.githubapp.features.users.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.githubapp.R
import com.example.githubapp.data.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(user: User, onClick: () -> Unit = {}) {
    OutlinedCard(onClick = { onClick() }, modifier = Modifier.fillMaxWidth().testTag(user.username)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(6.dp))

            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                model = user.avatarUrl,
                contentDescription = stringResource(
                    id = R.string.user_profile_image_description
                )
            )

            Spacer(Modifier.height(6.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = user.username + " (${user.id})",
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))
        }
    }
}

@Preview
@Composable
fun UserCardPreview() {
    UserCard(
        User(
            username = "mojombo",
            id = "1",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    )
}