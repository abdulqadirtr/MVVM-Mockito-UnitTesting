package com.example.mockito_mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mockito_mvvm.data.GithubRepoModel
import com.example.mockito_mvvm.data.GithubRepository
import com.example.mockito_mvvm.data.adapter.GitHubRepoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryViewModel (private val repository: GithubRepository) : ViewModel() {
    private val _repositories = MutableLiveData<ArrayList<GithubRepoModel>>()
    val repositories: LiveData<ArrayList<GithubRepoModel>> get() = _repositories

    var dataAdapter: GitHubRepoAdapter = GitHubRepoAdapter()

    fun getAdapter(): GitHubRepoAdapter {
        return dataAdapter
    }

    fun setAdapterData(data: ArrayList<GithubRepoModel>){
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }

    fun fetchRepositories(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val repos = repository.getRepositories(username)
            withContext(Dispatchers.Main) {
                _repositories.value = repos
            }
        }
    }
}