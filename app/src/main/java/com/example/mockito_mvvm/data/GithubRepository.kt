package com.example.mockito_mvvm.data

import com.example.mockito_mvvm.network.GithubApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubRepository  {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var githubApi: GithubApi? = retrofit.create(GithubApi::class.java)

    suspend fun getRepositories(username: String): ArrayList<GithubRepoModel>? {
        return githubApi?.getRepositories(username)
    }
}