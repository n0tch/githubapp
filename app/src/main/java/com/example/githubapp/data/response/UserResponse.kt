package com.example.githubapp.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("followers")
    val followers: Int? = null,
    @SerializedName("following")
    val following: Int? = null,
)
