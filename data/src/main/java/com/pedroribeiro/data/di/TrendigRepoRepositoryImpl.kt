package com.pedroribeiro.data.di

import com.pedroribeiro.data.network.Api
import com.pedroribeiro.domain.TrendingRepoRepository
import com.pedroribeiro.domain.models.RepositoryDomainModel
import io.reactivex.rxjava3.core.Single

class TrendigRepoRepositoryImpl(
    private val api: Api
) : TrendingRepoRepository {

    companion object {
        private const val LANGUAGE = "Kotlin"
        private const val SINCE = "today"
    }

    override fun getTrendingRepositories(): Single<List<RepositoryDomainModel>> {
        return api.getTrendingRepositories(
            LANGUAGE,
            SINCE
        ).map { entity ->
            entity.map {
                it.mapToDomain()
            }
        }
    }

}
