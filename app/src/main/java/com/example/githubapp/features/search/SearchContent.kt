package com.example.githubapp.features.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.githubapp.R
import com.example.githubapp.components.LoadingComponent
import com.example.githubapp.components.SearchBar
import com.example.githubapp.features.users.ui.UserList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    uiState: SearchState,
    onBackClicked: () -> Unit,
    onCardClicked: (userName: String) -> Unit = {},
    onSearch: (query: String) -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onBackClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button_text)
                    )
                }
                SearchBar(onSearchClicked = { searchText -> onSearch(searchText) })
            }

            if(uiState.isLoading){
                LoadingComponent()
            } else {
                uiState.count?.let {
                    Text(text = stringResource(R.string.search_result_count, it))
                }

                UserList(
                    modifier = Modifier,
                    users = uiState.users,
                    onCardClicked = { userName ->  onCardClicked(userName) }
                )
            }
        }
    }
}