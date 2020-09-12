package com.pedroribeiro.data.models

import com.pedroribeiro.domain.models.BuiltByDomainModel
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.domain.models.TrendingRepositoryDomainModel
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class TrendingRepositoriesEntity(
    @Json(name = "author")
    val author: String,
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "builtBy")
    val builtBy: List<BuiltBy>,
    @Json(name = "currentPeriodStars")
    val currentPeriodStars: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "forks")
    val forks: Int,
    @Json(name = "language")
    val language: String,
    @Json(name = "languageColor")
    val languageColor: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "stars")
    val stars: Int,
    @Json(name = "url")
    val url: String
) {
    fun mapToDomain() = RepositoryDomainModel(
        author,
        avatar,
        builtBy.map {
            it.mapToDomain()
        },
        currentPeriodStars,
        description,
        forks,
        language,
        languageColor,
        name,
        stars,
        url
    )
}

@JsonClass(generateAdapter = true)
data class BuiltBy(
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "href")
    val href: String,
    @Json(name = "username")
    val username: String
) {
    fun mapToDomain() = BuiltByDomainModel(
        avatar,
        href,
        username
    )
}