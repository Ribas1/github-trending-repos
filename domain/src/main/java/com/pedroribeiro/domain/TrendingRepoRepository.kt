package com.pedroribeiro.domain

import com.pedroribeiro.domain.models.RepositoryDomainModel
import io.reactivex.rxjava3.core.Single

interface TrendingRepoRepository {
    fun getTrendingRepositories(): Single<List<RepositoryDomainModel>>
}