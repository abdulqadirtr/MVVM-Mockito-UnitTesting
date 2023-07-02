package com.example.mockito_mvvm.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mockito_mvvm.data.GithubRepoModel
import com.example.mockito_mvvm.data.GithubRepository
import com.example.mockito_mvvm.data.Owner
import com.example.mockito_mvvm.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RepositoryViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    private lateinit var mockRepository: GithubRepository

    private lateinit var viewModel: RepositoryViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = RepositoryViewModel(mockRepository)
    }

    @Test
    fun testFetchRepositories() = runTest {
        val owner1 = Owner("Owner 1", 1, "https://example.com/avatar1.jpg")
        val owner2 = Owner("Owner 2", 2, "https://example.com/avatar2.jpg")

        val mockRepositories = arrayListOf(
            GithubRepoModel(1, "Repo 1", "Description 1", owner1, 10),
            GithubRepoModel(2, "Repo 2", "Description 2", owner2, 20)
        )

        `when`(mockRepository.getRepositories("abdulqadirtr")).thenReturn(mockRepositories)

        viewModel.fetchRepositories("abdulqadirtr")

        testDispatcher.scheduler.advanceUntilIdle()
        val repositories = viewModel.repositories.getOrAwaitValue()

        assertEquals(mockRepositories, repositories)
    }

    @After
    fun close(){
        Dispatchers.shutdown()
    }
}
