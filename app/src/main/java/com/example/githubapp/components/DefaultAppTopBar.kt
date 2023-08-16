package com.example.githubapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.githubapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    showSearchIcon: Boolean = false,
    showBackIcon: Boolean = false,
    onSearchClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(title) },
        navigationIcon = {
            if(showBackIcon){
                IconButton(onClick = { onBackClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_text)
                    )
                }
            }
        },
        actions = {
            if(showSearchIcon){
                IconButton(onClick = { onSearchClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.users_top_app_search_text),
                    )
                }
            }
        }
    )
}