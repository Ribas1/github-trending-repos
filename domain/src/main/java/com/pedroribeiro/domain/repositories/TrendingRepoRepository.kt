package com.pedroribeiro.domain.repositories

import com.pedroribeiro.domain.models.RepositoryDomainModel
import io.reactivex.rxjava3.core.Single

interface TrendingRepoRepository {
    fun getTrendingRepos(): Single<List<RepositoryDomainModel>>
    fun getTrendingReposFromDb(): Single<List<RepositoryDomainModel>>
}