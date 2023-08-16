package com.example.githubapp.data.api

import com.example.githubapp.data.response.SearchUserResponse
import com.example.githubapp.data.response.UserRepositoryResponse
import com.example.githubapp.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("/users")
    suspend fun fetchUsers(): Response<List<UserResponse>>

    @GET("/users/{userName}")
    suspend fun fetchUserDetail(@Path("userName") username: String): Response<UserResponse>

    @GET("/users/{userName}/repos")
    suspend fun fetchUserRepos(@Path("userName") userName: String): Response<List<UserRepositoryResponse>>

    @GET("/search/users")
    suspend fun searchUsers(@Query("q") query: String): Response<SearchUserResponse>
}
