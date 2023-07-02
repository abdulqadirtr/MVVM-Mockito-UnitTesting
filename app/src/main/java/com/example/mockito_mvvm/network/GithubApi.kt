package com.example.mockito_mvvm.network

import com.example.mockito_mvvm.data.GithubRepoModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String): ArrayList<GithubRepoModel>
}