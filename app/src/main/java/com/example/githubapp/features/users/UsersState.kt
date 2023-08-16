package com.example.githubapp.features.users

import com.example.githubapp.data.mapper.CustomException
import com.example.githubapp.data.model.User

data class UsersState(
    val isLoading: Boolean = false,
    val exception: CustomException? = null,
    val users: List<User> = emptyList()
)
