package com.example.githubapp.features.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.components.DefaultAppTopBar
import com.example.githubapp.components.EmptyContentComponent
import com.example.githubapp.components.ErrorLayout
import com.example.githubapp.components.LoadingComponent
import com.example.githubapp.features.users.ui.UserList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreenContent(
    usersState: UsersState,
    onSearchClicked: () -> Unit = {},
    onUserClicked: (userName: String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            DefaultAppTopBar(
                title = stringResource(R.string.users_top_app_bar_text),
                showSearchIcon = true,
                onSearchClicked = { onSearchClicked() }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            usersState.exception?.let { exception ->
                ErrorLayout(exception.messageRes)
            }

            if(usersState.isLoading){
                LoadingComponent()
            }

            if(usersState.users.isEmpty()){
                EmptyContentComponent()
            } else {
                UserList(
                    modifier = Modifier,
                    users = usersState.users,
                    onCardClicked = { onUserClicked(it) }
                )
            }
        }
    }
}