package com.pedroribeiro.domain.models

data class RepositoryDomainModel(
    val author: String,
    val avatar: String,
    val builtBy: List<BuiltByDomainModel>,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val name: String,
    val stars: Int,
    val url: String
)

data class BuiltByDomainModel(
    val avatar: String,
    val href: String,
    val username: String
)