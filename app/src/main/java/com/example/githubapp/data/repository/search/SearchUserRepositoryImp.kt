package com.example.githubapp.data.repository.search

import com.example.githubapp.Result
import com.example.githubapp.data.api.RemoteDataSource
import com.example.githubapp.data.mapper.ErrorMapper
import com.example.githubapp.data.mapper.SearchUserMapper
import com.example.githubapp.data.model.SearchUsers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchUserRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val searchUserMapper: SearchUserMapper,
    private val errorMapper: ErrorMapper,
    private val ioDispatcher: CoroutineDispatcher
): SearchUserRepository {

    override fun searchUsers(query: String): Flow<Result<SearchUsers>> = flow {
        val searchResponse = remoteDataSource.searchUsers(query)

        if (searchResponse.isSuccessful) {
            val user = searchUserMapper.mapToDomain(searchResponse.body())
            emit(Result.Success(user))
        } else {
            val exception = errorMapper.mapResponseCode(searchResponse.code())
            emit(Result.Error(exception))
        }
    }.catch {
        val exception = errorMapper.mapResponseException(it as Exception)
        emit(Result.Error(exception))
    }.flowOn(ioDispatcher)
}
