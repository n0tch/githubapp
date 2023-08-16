package com.example.githubapp.data.mapper

import com.example.githubapp.data.model.Repository
import com.example.githubapp.data.response.UserRepositoryResponse

class RepositoryMapper {

    fun mapListToDomain(
        repositoryResponse: List<UserRepositoryResponse>?
    ) = repositoryResponse?.map {
        Repository(
            name = it.name ?: "",
            visibility = it.visibility ?: "",
            description = it.description ?: "",
            url = it.url ?: "",
            language = it.language ?: "",
            stars = it.stars ?: 0
        )
    } ?: emptyList()
}