package com.example.githubapp.data.mapper

import com.example.githubapp.data.model.User
import com.example.githubapp.data.response.UserResponse
import com.example.githubapp.mocks.UserResponseMock
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class UserMapperTest{

    private val userMapper = UserMapper()

    @Test
    fun `WHEN map user response it SHOULD return a user with same data content`(){
        val userResponse = UserResponseMock.userResponseListMock()
        val userMapped = userMapper.mapListToDomain(userResponse)

        userResponse.zip(userMapped){ response, mapped ->
            assertUsers(response, mapped)
        }
    }

    @Test
    fun `WHEN map user detail response it SHOULD return a user with same data content`(){
        val userResponse = UserResponseMock.simpleUserResponseMock()
        val userMapped = userMapper.mapToUserDetail(userResponse)

        assertEquals(userResponse.bio, userMapped.bio)
        assertEquals(userResponse.company, userMapped.company)
        assertEquals(userResponse.blog, userMapped.blog)
        assertEquals(userResponse.location, userMapped.location)
        assertEquals(userResponse.email, userMapped.email)
        assertEquals(userResponse.followers, userMapped.followers)
        assertEquals(userResponse.following, userMapped.following)
    }

    @Test
    fun `WHEN map user detail response with some null values it SHOULD return a user with same data content`(){
        val userResponse = UserResponseMock.userResponseWithNullValues()
        val userMapped = userMapper.mapToUserDetail(userResponse)

        assertTrue(userMapped.bio.isEmpty())
        assertTrue(userMapped.company.isEmpty())
        assertTrue(userMapped.location.isEmpty())
        assertTrue(userMapped.blog.isEmpty())
        assertTrue(userMapped.name.isEmpty())
        assertEquals(0, userMapped.followers)
        assertEquals(0, userMapped.following)
    }

    @Test
    fun `WHEN there is a null user response it SHOULD map with default values`(){
        val userMapped = userMapper.mapToUserDetail(null)

        assertTrue(userMapped.bio.isEmpty())
        assertTrue(userMapped.company.isEmpty())
        assertTrue(userMapped.location.isEmpty())
        assertTrue(userMapped.blog.isEmpty())
        assertTrue(userMapped.name.isEmpty())
        assertEquals(0, userMapped.followers)
        assertEquals(0, userMapped.following)
    }

    private fun assertUsers(response: UserResponse, mapped: User) {
        assertEquals(response.id, mapped.id)
        assertEquals(response.login, mapped.username)
        assertEquals(response.avatarUrl, mapped.avatarUrl)
    }
}