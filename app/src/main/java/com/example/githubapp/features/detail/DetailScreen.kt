package com.example.githubapp.features.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.components.DefaultAppTopBar
import com.example.githubapp.components.ErrorLayout
import com.example.githubapp.components.LoadingComponent
import com.example.githubapp.data.mapper.CustomException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    userName: String,
    onBackClicked: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            DefaultAppTopBar(
                title = userName,
                showBackIcon = true,
                onBackClicked = { onBackClicked() })
        },
    ) {

        val userState by viewModel.userDetailState.collectAsState()

        LaunchedEffect(key1 = Unit, block = {
            viewModel.fetchUserInfo(userName)
        })

        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 4.dp)
        ) {
            if (userState.isLoading) {
                LoadingComponent()
            } else if(userState.exception != null) {
                ErrorLayout(errorMessage = (userState.exception as CustomException).messageRes, onRetryButtonClicked = {
                    viewModel.fetchUserInfo(userName)
                })
            } else {
                DetailContent(userState.userDetail, userState.repositories)
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(userName = "mojombo")
}