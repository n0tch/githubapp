package com.example.githubapp.data.response

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("total_count")
    val count: Int?,
    @SerializedName("items")
    val users: List<UserResponse>?
)
