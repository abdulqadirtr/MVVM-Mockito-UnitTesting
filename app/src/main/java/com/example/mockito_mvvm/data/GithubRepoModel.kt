package com.example.mockito_mvvm.data

data class Owner(
    val login: String?,
    val id: Long?,
    val avatarUrl: String?
)

data class GithubRepoModel(
    val id: Long?,
    val name: String?,
    val description: String?,
    val owner: Owner,
    val stargazersCount: Int?
)
