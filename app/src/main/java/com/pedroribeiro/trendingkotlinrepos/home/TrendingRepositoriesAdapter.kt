package com.pedroribeiro.trendingkotlinrepos.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.trendingkotlinrepos.R
import com.pedroribeiro.trendingkotlinrepos.models.RepositoryUiModel
import kotlinx.android.synthetic.main.item_repository.view.*

class TrendingRepositoriesAdapter(
    private val onClickListener: (RepositoryUiModel) -> Unit
) : RecyclerView.Adapter<TrendingRepositoriesViewHolder>() {

    private val trendingRepositories = mutableListOf<RepositoryUiModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingRepositoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return TrendingRepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingRepositoriesViewHolder, position: Int) {
        val repository = trendingRepositories[position]
        holder.bind(repository, onClickListener)
    }

    override fun getItemCount(): Int = trendingRepositories.size

    fun setData(repos: List<RepositoryUiModel>) {
        trendingRepositories.clear()
        trendingRepositories.addAll(repos)
        notifyDataSetChanged()
    }

}

class TrendingRepositoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(repository: RepositoryUiModel, onClickListener: (RepositoryUiModel) -> Unit) {
        itemView.tv_item_repo_title.text = repository.name
        itemView.tv_item_repo_description.text = repository.description
        Glide.with(itemView.context)
            .load(repository.avatar)
            .into(itemView.iv_avatar)
        itemView.setOnClickListener {
            onClickListener(repository)
        }
    }

}
