package com.pedroribeiro.trendingkotlinrepos.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.trendingkotlinrepos.R
import kotlinx.android.synthetic.main.item_repository.view.*

class TrendingRepositoriesAdapter : RecyclerView.Adapter<TrendingRepositoriesViewHolder>() {

    private val trendingRepositories = mutableListOf<RepositoryDomainModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingRepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return TrendingRepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingRepositoriesViewHolder, position: Int) {
        val repository = trendingRepositories[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int = trendingRepositories.size

    fun setData(repos: List<RepositoryDomainModel>) {
        trendingRepositories.clear()
        trendingRepositories.addAll(repos)
        notifyDataSetChanged()
    }

}

class TrendingRepositoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(repository: RepositoryDomainModel) {
        itemView.tv_item_repo_title.text = repository.name
        itemView.tv_item_repo_description.text = repository.description
    }

}