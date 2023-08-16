package com.example.githubapp.data.response

import com.google.gson.annotations.SerializedName

data class UserRepositoryResponse(
    @SerializedName("full_name")
    val name: String? = "",
    @SerializedName("visibility")
    val visibility: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("stargazers_count")
    val stars: Int? = 0
)
