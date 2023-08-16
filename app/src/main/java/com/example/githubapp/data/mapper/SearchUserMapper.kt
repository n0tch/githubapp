package com.example.githubapp.data.mapper

import com.example.githubapp.data.model.SearchUsers
import com.example.githubapp.data.response.SearchUserResponse
import javax.inject.Inject

class SearchUserMapper @Inject constructor(
    private val userMapper: UserMapper
) {
    fun mapToDomain(
        searchUserResponse: SearchUserResponse?
    ) = SearchUsers(
        count = searchUserResponse?.count ?: 0,
        users = userMapper.mapListToDomain(searchUserResponse?.users),
    )
}
