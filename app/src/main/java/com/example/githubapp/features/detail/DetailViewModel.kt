package com.example.githubapp.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.Result
import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.model.UserDetail
import com.example.githubapp.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val userDetailState: MutableStateFlow<DetailState> by lazy { MutableStateFlow(DetailState(isLoading = true)) }

    fun fetchUserInfo(userName: String) {
        viewModelScope.launch {
            userRepository.fetchUserDetail(userName)
                .combine(userRepository.fetchUserRepositories(userName)) { userDetail, userRepositories ->
                    DetailStateResult(userDetail, userRepositories)
                }.onStart {

                }.collect {
                    handleResult(it.userDetailResult, it.userRepositoriesResult)
                }
        }
    }

    private suspend fun handleResult(
        userDetail: Result<UserDetail>,
        userRepositoriesResult: Result<List<Repository>>
    ) {
        if(userDetail is Result.Success && userRepositoriesResult is Result.Success){
            userDetailState.emit(DetailState(userDetail = userDetail.data, repositories = userRepositoriesResult.data))
        } else if (userDetail is Result.Error){
            userDetailState.emit(DetailState(exception = userDetail.exception))
        } else if(userRepositoriesResult is Result.Error){
            userDetailState.emit(DetailState(exception = userRepositoriesResult.exception))
        }
    }

}