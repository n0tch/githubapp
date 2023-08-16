package com.example.githubapp.features.detail

import com.example.githubapp.Result
import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.model.UserDetail

data class DetailStateResult(
    val userDetailResult: Result<UserDetail>,
    val userRepositoriesResult: Result<List<Repository>>
)