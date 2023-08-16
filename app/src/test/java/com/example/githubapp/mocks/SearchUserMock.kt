package com.example.githubapp.mocks

import com.example.githubapp.data.model.SearchUsers
import com.example.githubapp.data.model.User

object SearchUserMock {

    fun simpleSearchUser() = SearchUsers(count = 1, users= listOf(UserMock.simpleUser()))

}