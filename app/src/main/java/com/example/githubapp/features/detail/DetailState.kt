package com.example.githubapp.features.detail

import com.example.githubapp.data.mapper.CustomException
import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.model.UserDetail

data class DetailState(
    val userDetail: UserDetail = UserDetail(),
    val repositories: List<Repository> = emptyList(),
    val isLoading: Boolean = false,
    val exception: CustomException? = null,
)
