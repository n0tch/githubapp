package com.example.githubapp.mocks

import com.example.githubapp.data.response.UserResponse

object UserResponseMock {

    fun simpleUserResponseMock() = UserResponse(
        login = "my-username",
        id = "my-id",
        avatarUrl = "https://my-avatar-url.com",
        name = "my-name",
        company = "my-company",
        blog = "my-blog",
        location = "my-location",
        email = "email@email.com",
        bio = "my-bio",
        followers = 0,
        following = 1
    )

    fun userResponseWithNullValues() = UserResponse(
        login = "my-username",
        id = "my-id",
        avatarUrl = "https://my-avatar-url.com",
        name = null,
        company = null,
        blog = null,
        location = null,
        email = null,
        bio = null,
        followers = null,
        following = null
    )

    fun userResponseListMock() = listOf(
        simpleUserResponseMock(),
        simpleUserResponseMock()
    )
}