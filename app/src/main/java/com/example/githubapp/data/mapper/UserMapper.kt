package com.example.githubapp.data.mapper

import com.example.githubapp.data.model.User
import com.example.githubapp.data.model.UserDetail
import com.example.githubapp.data.response.UserResponse

class UserMapper {

    fun mapListToDomain(response: List<UserResponse>?): List<User> =
        response?.map {
            User(
                username = it.login ?: "",
                id = it.id ?: "",
                avatarUrl = it.avatarUrl ?: ""
            )
        } ?: emptyList()

    fun mapToUserDetail(userResponse: UserResponse?) = UserDetail(
        avatarUrl = userResponse?.avatarUrl ?: "",
        email = userResponse?.email ?: "",
        name = userResponse?.name ?: "",
        bio = userResponse?.bio ?: "",
        company = userResponse?.company ?: "",
        blog = userResponse?.blog ?: "",
        location = userResponse?.location ?: "",
        followers = userResponse?.followers ?: 0,
        following = userResponse?.following ?: 0
    )
}