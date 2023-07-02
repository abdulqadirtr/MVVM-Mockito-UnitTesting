package com.example.mockito_mvvm.data

import com.example.mockito_mvvm.network.GithubApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GithubRepositoryTest {

    @Mock
    private lateinit var githubApi: GithubApi

    private lateinit var githubRepository: GithubRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        githubRepository = GithubRepository()
        githubRepository.githubApi = githubApi
    }

    @Test
    fun testGetEmptyRepositories() = runBlocking {
        val username = "testuser"
        val expectedRepositories = ArrayList<GithubRepoModel>()

        // Mock the API response
        Mockito.`when`(githubApi.getRepositories(username)).thenReturn(expectedRepositories)

        // Call the method under test
        val result = githubRepository.getRepositories(username)

        // Verify that the API method is called with the correct username
        Mockito.verify(githubApi).getRepositories(username)

        // Verify that the result matches the expected repositories
        assert(result == expectedRepositories)
    }

    @Test
    fun testGetExpectedRepositories() = runBlocking {
        val username = "testuser"
        val owner1 = Owner("Owner 1", 1, "https://example.com/avatar1.jpg")
        val owner2 = Owner("Owner 2", 2, "https://example.com/avatar2.jpg")

        val expectedRepositories = arrayListOf(
            GithubRepoModel(1, "Repo 1", "Description 1", owner1, 10),
            GithubRepoModel(2, "Repo 2", "Description 2", owner2, 20)
        )

        // Mock the API response
        Mockito.`when`(githubApi.getRepositories(username)).thenReturn(expectedRepositories)

        // Call the method under test
        val result = githubRepository.getRepositories(username)

        // Verify that the API method is called with the correct username
        Mockito.verify(githubApi).getRepositories(username)

        // Verify that the result matches the expected repositories
        assert(2 == result!!.size)
    }
}