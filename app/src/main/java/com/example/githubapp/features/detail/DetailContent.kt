package com.example.githubapp.features.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.githubapp.components.EmptyContentComponent
import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.model.UserDetail
import com.example.githubapp.features.detail.ui.RepositoryCard
import com.example.githubapp.features.detail.ui.UserDetailCard

@Composable
fun DetailContent(userDetail: UserDetail, repositories: List<Repository>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            UserDetailCard(userDetail)
        }

        if(repositories.isEmpty()){
            item{ EmptyContentComponent() }
        } else {
            items(repositories) { repository ->
                RepositoryCard(repository)
            }
        }
    }
}