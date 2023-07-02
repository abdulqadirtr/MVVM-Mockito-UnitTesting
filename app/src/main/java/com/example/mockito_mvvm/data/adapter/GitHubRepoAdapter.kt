package com.example.mockito_mvvm.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mockito_mvvm.data.GithubRepoModel
import com.example.mockito_mvvm.databinding.RecyclerLayoutBinding

class GitHubRepoAdapter : RecyclerView.Adapter<GitHubRepoAdapter.GitHubHolder>() {

    var items = ArrayList<GithubRepoModel>()
    lateinit var binding: RecyclerLayoutBinding

    fun setData(data: ArrayList<GithubRepoModel>){
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubHolder {
        binding = RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitHubHolder(binding.root)
    }

    override fun onBindViewHolder(holder: GitHubHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

  inner  class GitHubHolder(items: View): RecyclerView.ViewHolder(items) {

        fun bind(repo: GithubRepoModel){
            binding.repo = repo
            binding.executePendingBindings()
        }
    }
}