package com.example.githubapp.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.Result
import com.example.githubapp.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {

    val usersState = userRepository
        .fetchUsers()
        .map {
            when (it) {
                is Result.Error -> UsersState(exception = it.exception)
                is Result.Success -> UsersState(users = it.data)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UsersState(isLoading = true)
        )
}
