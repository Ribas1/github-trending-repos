package com.pedroribeiro.trendingkotlinrepos.models

import android.os.Parcelable
import com.pedroribeiro.domain.models.BuiltByDomainModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryUiModel(
    val author: String,
    val avatar: String,
    val builtBy: List<BuiltByUiModel>,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val name: String,
    val stars: Int,
    val url: String
) : Parcelable

@Parcelize
data class BuiltByUiModel(
    val avatar: String,
    val username: String
) : Parcelable