package com.example.githubapp.data.repository.user

import com.example.githubapp.Result
import com.example.githubapp.data.api.RemoteDataSource
import com.example.githubapp.data.mapper.ErrorMapper
import com.example.githubapp.data.mapper.RepositoryMapper
import com.example.githubapp.data.mapper.UserMapper
import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.model.User
import com.example.githubapp.data.model.UserDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val userMapper: UserMapper,
    private val repositoryMapper: RepositoryMapper,
    private val errorMapper: ErrorMapper,
    private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    override fun fetchUsers(): Flow<Result<List<User>>> = flow {
        val usersResponse =
            remoteDataSource.fetchUsers()
        if (usersResponse.isSuccessful) {
            val userList = userMapper.mapListToDomain(usersResponse.body())
            emit(Result.Success(userList))
        } else {
            val exception = errorMapper.mapResponseCode(usersResponse.code())
            emit(Result.Error(exception))
        }
    }.catch {
        val exception = errorMapper.mapResponseException(it as Exception)
        emit(Result.Error(exception))
    }.flowOn(ioDispatcher)

    override fun fetchUserDetail(userName: String): Flow<Result<UserDetail>> = flow {
        val userResponse = remoteDataSource.fetchUserDetail(userName)
        if (userResponse.isSuccessful) {
            val user = userMapper.mapToUserDetail(userResponse.body())
            emit(Result.Success(user))
        } else {
            val exception = errorMapper.mapResponseCode(userResponse.code())
            emit(Result.Error(exception))
        }
    }.catch {
        val exception = errorMapper.mapResponseException(it as Exception)
        emit(Result.Error(exception))
    }.flowOn(ioDispatcher)

    override fun fetchUserRepositories(userName: String): Flow<Result<List<Repository>>> = flow {
        val repositoryResponse = remoteDataSource.fetchUserRepos(userName)

        if (repositoryResponse.isSuccessful) {
            val repository = repositoryMapper.mapListToDomain(repositoryResponse.body())
            emit(Result.Success(repository))
        } else {
            val exception = errorMapper.mapResponseCode(repositoryResponse.code())
            emit(Result.Error(exception))
        }
    }.catch {
        val exception = errorMapper.mapResponseException(it as Exception)
        emit(Result.Error(exception))
    }.flowOn(ioDispatcher)
}
