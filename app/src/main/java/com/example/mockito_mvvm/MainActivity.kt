package com.example.mockito_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mockito_mvvm.databinding.ActivityMainBinding
import com.example.mockito_mvvm.viewmodels.RepositoryViewModel
import com.example.mockito_mvvm.viewmodels.RepositoryViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: RepositoryViewModel

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, RepositoryViewModelFactory())[RepositoryViewModel::class.java]
        viewModel.fetchRepositories("abdulqadirtr")

        binding.viewModel = viewModel
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }

        viewModel.repositories.observe(this, Observer { repositories ->
            // Handle the updated repository data here
            Log.e("repo data", repositories.size.toString())
            viewModel.setAdapterData(repositories)
        })


    }
}