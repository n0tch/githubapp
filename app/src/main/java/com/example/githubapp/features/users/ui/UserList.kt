package com.example.githubapp.features.users.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubapp.data.model.User

const val USER_LIST_TAG = "user_list_tag"

@Composable
fun UserList(
    modifier: Modifier,
    users: List<User>,
    onCardClicked: (userName: String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.semantics { testTag = USER_LIST_TAG },
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(users, key = { it.id }) {
            UserCard(user = it, onClick = { onCardClicked(it.username) })
        }
    }
}

@Preview
@Composable
fun UserListPreview() {
    UserList(modifier = Modifier, users = listOf(), onCardClicked = {})
}