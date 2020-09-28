package com.pedroribeiro.data.di

import com.pedroribeiro.data.db.TrendingReposDao
import com.pedroribeiro.data.models.TrendingRepositoriesEntity
import com.pedroribeiro.data.network.Api
import com.pedroribeiro.data.network.exceptions.EmptyDatabaseException
import com.pedroribeiro.domain.repositories.TrendingRepoRepository
import com.pedroribeiro.domain.models.RepositoryDomainModel
import io.reactivex.rxjava3.core.Single

class TrendigRepoRepositoryImpl(
    private val reposDao: TrendingReposDao,
    private val api: Api
) : TrendingRepoRepository {

    companion object {
        private const val LANGUAGE = "Kotlin"
        private const val SINCE = "today"
    }

    override fun getTrendingRepos(): Single<List<RepositoryDomainModel>> {
        return api.getTrendingRepositories(
            LANGUAGE,
            SINCE
        ).doOnSuccess { repos ->
            manageDb(repos)
        }.map { entity ->
            entity.map {
                it.mapToDomain()
            }
        }
    }

    override fun getTrendingReposFromDb(): Single<List<RepositoryDomainModel>> {
        return Single.just(reposDao.getTrendingRepos())
            .map { entity ->
                entity.map {
                    it.mapToDomain()
                }
            }.flatMap {
                if (it.isEmpty()) {
                    Single.error(EmptyDatabaseException())
                } else {
                    Single.just(it)
                }
            }
    }

    private fun manageDb(repos: List<TrendingRepositoriesEntity>): Single<List<Long>> {
        return Single.just(reposDao.saveRepos(repos))
    }
}