package com.example.githubapp.features.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    onUserClicked: (userName: String) -> Unit = {},
    onSearchClicked: () -> Unit = {}
) {
    val usersState by viewModel.usersState.collectAsState()
    UserScreenContent(
        usersState = usersState,
        onSearchClicked = onSearchClicked,
        onUserClicked = onUserClicked
    )
}

@Preview
@Composable
fun UserScreenPreview() {
    UsersScreen()
}