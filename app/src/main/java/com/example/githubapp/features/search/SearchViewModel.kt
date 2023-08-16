package com.example.githubapp.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.Result
import com.example.githubapp.data.repository.search.SearchUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUserRepository: SearchUserRepository
): ViewModel() {

    val uiState by lazy { MutableStateFlow(SearchState()) }

    fun searchUser(query: String) {
        viewModelScope.launch {
            searchUserRepository
                .searchUsers(query)
                .onStart {
                    uiState.emit(SearchState(isLoading = true))
                }
                .collect {
                    when (it) {
                        is Result.Error -> {
                            uiState.emit(SearchState(exception = it.exception))
                        }

                        is Result.Success -> {
                            uiState.emit(SearchState(count = it.data.count, users = it.data.users))
                        }
                    }
                }
        }
    }

}