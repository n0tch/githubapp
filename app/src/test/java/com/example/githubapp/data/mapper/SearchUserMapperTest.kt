package com.example.githubapp.data.mapper

import com.example.githubapp.data.model.User
import com.example.githubapp.mocks.SearchUserResponseMock
import com.example.githubapp.mocks.UserMock.simpleUserList
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class SearchUserMapperTest{

    private val userMapper = mockk<UserMapper>()
    private val searchUserMapper = SearchUserMapper(userMapper)

    @Test
    fun `WHEN map search user it SHOULD map with correct values`(){
        val users = simpleUserList()
        val searchUserResponse = SearchUserResponseMock.dummySearchResponse()

        every { userMapper.mapListToDomain(searchUserResponse.users) } returns users

        val searchUser = searchUserMapper.mapToDomain(searchUserResponse)

        assertEquals(searchUserResponse.count, searchUser.count)

        searchUserResponse.users?.zip(users){ response, user ->
            assertEquals(response.id, user.id)
            assertEquals(response.login, user.username)
            assertEquals(response.avatarUrl, user.avatarUrl)
        }
    }

    @Test
    fun `WHEN map search user with NULL it SHOULD map with default values`(){
        val users = emptyList<User>()
        val searchUserResponse = SearchUserResponseMock.searchResponseWithNullValues()

        every { userMapper.mapListToDomain(searchUserResponse.users) } returns users

        val searchUser = searchUserMapper.mapToDomain(searchUserResponse)

        assertEquals(0, searchUser.count)
        assertTrue(searchUser.users.isEmpty())
    }
}
