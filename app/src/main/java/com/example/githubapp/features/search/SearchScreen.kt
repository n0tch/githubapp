package com.example.githubapp.features.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.components.ErrorLayout
import com.example.githubapp.data.mapper.CustomException

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBackClicked: () -> Unit = {},
    onCardClicked: (userName: String) -> Unit = { }
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.exception != null) {
        ErrorLayout(errorMessage = (uiState.exception as CustomException).messageRes)
    } else {
        SearchContent(
            uiState = uiState,
            onBackClicked = onBackClicked,
            onCardClicked = onCardClicked,
            onSearch = viewModel::searchUser
        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}