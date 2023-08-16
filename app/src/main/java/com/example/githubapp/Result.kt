package com.example.githubapp

import com.example.githubapp.data.mapper.CustomException

sealed interface Result<out T>{
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: CustomException) : Result<Nothing>
}