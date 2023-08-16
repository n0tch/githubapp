package com.example.githubapp.features.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.githubapp.R
import com.example.githubapp.components.TextIfNotEmpty
import com.example.githubapp.components.TextWithIcon
import com.example.githubapp.data.model.UserDetail

@Composable
fun UserDetailCard(userDetail: UserDetail) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(6.dp))
        AsyncImage(
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape),
            model = userDetail.avatarUrl,
            contentDescription = stringResource(id = R.string.user_profile_image_description)
        )
        TextIfNotEmpty(modifier = Modifier, userDetail.name)
        Spacer(Modifier.height(6.dp))
        TextIfNotEmpty(modifier = Modifier.widthIn(max = 200.dp), userDetail.bio)

        Row {
            TextWithIcon(
                Icons.Filled.Person,
                stringResource(id = R.string.followers_count_text, userDetail.followers)
            )
            Spacer(Modifier.width(6.dp))
            TextWithIcon(
                Icons.Filled.Person,
                stringResource(id = R.string.following_count_text, userDetail.following)
            )
        }
        TextWithIcon(icon = Icons.Filled.LocationOn, text = userDetail.location)
        TextWithIcon(icon = Icons.Filled.Info, text = userDetail.company)
        TextWithIcon(icon = Icons.Filled.Email, text = userDetail.email)
    }
}

@Preview
@Composable
fun UserDetailCardPreview() {
    UserDetailCard(
        UserDetail("", "email", "name", "bio", "company", "blog", "location", 12, 13)
    )
}