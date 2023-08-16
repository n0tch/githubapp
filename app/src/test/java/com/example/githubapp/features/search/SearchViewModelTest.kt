package com.example.githubapp.features.search

import app.cash.turbine.test
import com.example.githubapp.MainCoroutineRule
import com.example.githubapp.Result
import com.example.githubapp.data.mapper.CustomException
import com.example.githubapp.data.repository.search.SearchUserRepository
import com.example.githubapp.mocks.SearchUserMock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest{

    private val searchRepository = mockk<SearchUserRepository>()
    private val searchViewModel = SearchViewModel(searchRepository)

    private val query = "search text"

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `WHEN search users from repository it SHOULD set initial value and repository response values`() = runTest {
        val searchUsers = SearchUserMock.simpleSearchUser()
        val searchUserResult = flowOf(Result.Success(searchUsers))

        coEvery { searchRepository.searchUsers(query) } returns searchUserResult

        searchViewModel.uiState.test {
            searchViewModel.searchUser(query)

            val initialFlowValue = awaitItem()
            val loadingValue = awaitItem()
            val item = awaitItem()

            //initial ui state
            assertNull(initialFlowValue.count)
            assertTrue(initialFlowValue.users.isEmpty())

            //loading ui staste
            assertTrue(loadingValue.isLoading)

            //ui with repository state
            assertEquals(searchUsers.count, item.count)
            assertEquals(searchUsers.users, item.users)
        }
    }

    @Test
    fun `WHEN search users from repository with error it SHOULD set initial value and repository response values`() = runTest {
        val exception = CustomException.InternetErrorException()
        val searchUserResult = flowOf(Result.Error(exception))

        coEvery { searchRepository.searchUsers(query) } returns searchUserResult

        searchViewModel.uiState.test {
            searchViewModel.searchUser(query)

            val initialFlowValue = awaitItem()
            val loadingValue = awaitItem()
            val item = awaitItem()

            //initial ui state
            assertNull(initialFlowValue.count)
            assertTrue(initialFlowValue.users.isEmpty())

            //loading ui staste
            assertTrue(loadingValue.isLoading)

            //ui with repository state
            assertNull(item.count)
            assertTrue(item.users.isEmpty())
            assertEquals(item.exception?.messageRes, exception.messageRes)
        }
    }

    @Test
    fun `WHEN search user it SHOULD call repository correct method`() = runTest {
        val searchUsers = SearchUserMock.simpleSearchUser()
        val searchUserResult = flowOf(Result.Success(searchUsers))

        coEvery { searchRepository.searchUsers(query) } returns searchUserResult

        searchViewModel.uiState.test {
            searchViewModel.searchUser(query)
            coVerify { searchRepository.searchUsers(query) }
            awaitItem()
            awaitItem()
            awaitItem()
        }
    }
}
