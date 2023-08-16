package com.example.githubapp.data.repository.search

import app.cash.turbine.test
import com.example.githubapp.Result
import com.example.githubapp.data.api.RemoteDataSource
import com.example.githubapp.data.mapper.CustomException
import com.example.githubapp.data.mapper.ErrorMapper
import com.example.githubapp.data.mapper.SearchUserMapper
import com.example.githubapp.data.response.SearchUserResponse
import com.example.githubapp.mocks.SearchUserMock
import com.example.githubapp.mocks.SearchUserResponseMock
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import java.net.UnknownHostException

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class SearchUserRepositoryImpTest {

    private val remoteDataSourceMock = mockk<RemoteDataSource>()
    private val searchUserMapper = mockk<SearchUserMapper>()
    private val errorMapper = mockk<ErrorMapper>()

    private val query = "search text"

    private val userRepository = SearchUserRepositoryImp(
        remoteDataSourceMock,
        searchUserMapper,
        errorMapper,
        Dispatchers.Unconfined
    )

    @Test
    fun `WHEN search for a user AND there is result from api it SHOULD return SearchUser data`() =
        runTest {
            val remoteResponse = Response.success(SearchUserResponseMock.dummySearchResponse())
            val searchUsers = SearchUserMock.simpleSearchUser()

            every { searchUserMapper.mapToDomain(remoteResponse.body()) } returns searchUsers
            coEvery { remoteDataSourceMock.searchUsers(query) } returns remoteResponse

            userRepository.searchUsers(query).test {
                Assert.assertEquals(awaitItem(), Result.Success(searchUsers))
                awaitComplete()
            }
        }

    @Test
    fun `WHEN there is a UnknownHostException it SHOULD map to correct exception`() = runTest {
        val exception = UnknownHostException()
        val customException = CustomException.InternetErrorException()
        val expectedResult = Result.Error(customException)

        every { errorMapper.mapResponseException(exception) } returns customException
        coEvery { remoteDataSourceMock.searchUsers(query) } throws exception

        userRepository.searchUsers(query).test {
            Assert.assertEquals(awaitItem(), expectedResult)
            awaitComplete()
        }
    }

    @Test
    fun `WHEN API returns an error body with 503 as code it SHOULD map to UnavailableException`() =
        runTest {
            val customException = CustomException.UnavailableException()
            val apiResponse = Response.error<SearchUserResponse>(503, "".toResponseBody())
            val expectedResult = Result.Error(customException)

            every { errorMapper.mapResponseCode(apiResponse.code()) } returns customException
            coEvery { remoteDataSourceMock.searchUsers(query) } returns apiResponse

            userRepository.searchUsers(query).test {
                val output = awaitItem()
                Assert.assertEquals(output, expectedResult)
                awaitComplete()
            }
        }

    @Test
    fun `WHEN API returns an unmapped error it SHOULD return UnknownException`() = runTest {
        val customException = CustomException.UnknownException()
        val apiResponse = Response.error<SearchUserResponse>(504, "".toResponseBody())
        val expectedResult = Result.Error(customException)

        every { errorMapper.mapResponseCode(apiResponse.code()) } returns customException
        coEvery { remoteDataSourceMock.searchUsers(query) } returns apiResponse

        userRepository.searchUsers(query).test {
            val output = awaitItem()
            Assert.assertEquals(output, expectedResult)
            awaitComplete()
        }
    }

    @Test
    fun `WHEN API throws an unmapped exception it SHOULD return UnknownException`() = runTest {
        val exception = NullPointerException()
        val customException = CustomException.UnknownException()
        val expectedResult = Result.Error(customException)

        every { errorMapper.mapResponseException(exception) } returns customException
        coEvery { remoteDataSourceMock.searchUsers(query) } throws exception

        userRepository.searchUsers(query).test {
            Assert.assertEquals(awaitItem(), expectedResult)
            awaitComplete()
        }
    }
}