package com.example.githubapp.features.search

import com.example.githubapp.data.mapper.CustomException
import com.example.githubapp.data.model.User

data class SearchState(
    val count: Int? = null,
    val users: List<User> = emptyList(),
    val exception: CustomException? = null,
    val isLoading: Boolean = false
)
