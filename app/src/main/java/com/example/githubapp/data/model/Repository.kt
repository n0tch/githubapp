package com.example.githubapp.data.model

data class Repository(
    val name: String,
    val visibility: String,
    val description: String,
    val url: String,
    val language: String,
    val stars: Int
)
