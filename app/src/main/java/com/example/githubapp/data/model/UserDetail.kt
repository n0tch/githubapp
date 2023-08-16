package com.example.githubapp.data.model

data class UserDetail(
    val avatarUrl: String = "",
    val email: String = "",
    val name: String = "",
    val bio: String = "",
    val company: String = "",
    val blog: String = "",
    val location: String = "",
    val followers: Int = 0,
    val following: Int = 0,
)
