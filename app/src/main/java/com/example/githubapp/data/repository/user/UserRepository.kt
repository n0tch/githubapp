package com.example.githubapp.data.repository.user

import com.example.githubapp.Result
import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.model.SearchUsers
import com.example.githubapp.data.model.User
import com.example.githubapp.data.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun fetchUsers(): Flow<Result<List<User>>>

    fun fetchUserDetail(userName: String): Flow<Result<UserDetail>>

    fun fetchUserRepositories(userName: String): Flow<Result<List<Repository>>>
}
