package com.example.mockito_mvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mockito_mvvm.data.GithubRepository
import kotlin.jvm.Throws

class RepositoryViewModelFactory : ViewModelProvider.Factory {

    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoryViewModel(repository = GithubRepository()) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}