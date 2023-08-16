package com.example.githubapp.mocks

import com.example.githubapp.data.model.User

object UserMock {
    fun simpleUser() = User(
        id = "my-id",
        username = "my-username",
        avatarUrl = "https://my-avatar-url.com"
    )

    fun simpleUserList() = listOf(
        simpleUser()
    )
}