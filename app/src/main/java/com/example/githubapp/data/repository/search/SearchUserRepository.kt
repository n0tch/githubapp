package com.example.githubapp.data.repository.search

import com.example.githubapp.Result
import com.example.githubapp.data.model.SearchUsers
import kotlinx.coroutines.flow.Flow

interface SearchUserRepository {
    fun searchUsers(query: String): Flow<Result<SearchUsers>>
}