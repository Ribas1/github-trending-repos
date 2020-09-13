package com.pedroribeiro.trendingkotlinrepos.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedroribeiro.trendingkotlinrepos.R
import com.pedroribeiro.trendingkotlinrepos.models.BuiltByUiModel
import kotlinx.android.synthetic.main.item_details_contributers.view.*

class ContributorsAdapter(
    private val contributors: List<BuiltByUiModel>
) : RecyclerView.Adapter<ContributorsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_details_contributers, parent, false)
        return ContributorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContributorsViewHolder, position: Int) {
        val contributor = this.contributors[position]
        holder.bind(contributor)
    }

    override fun getItemCount(): Int = this.contributors.size

}

class ContributorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(contributor: BuiltByUiModel) {
        Glide.with(itemView.context)
            .load(contributor.avatar)
            .circleCrop()
            .into(itemView.iv_contributor_avatar)
        itemView.tv_contributor_username.text = contributor.username
    }

}
