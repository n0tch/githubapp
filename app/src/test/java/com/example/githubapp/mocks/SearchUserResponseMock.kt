package com.example.githubapp.mocks

import com.example.githubapp.data.response.SearchUserResponse
import com.example.githubapp.mocks.UserResponseMock.userResponseListMock

object SearchUserResponseMock {

    fun dummySearchResponse() = SearchUserResponse(
        count = 1,
        users = userResponseListMock()
    )

    fun searchResponseWithNullValues() = SearchUserResponse(
        count = null,
        users = null
    )
}
