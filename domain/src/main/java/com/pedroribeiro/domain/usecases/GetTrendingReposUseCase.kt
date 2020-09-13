package com.pedroribeiro.domain.usecases

import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.domain.repositories.TrendingRepoRepository
import io.reactivex.rxjava3.core.Single

class GetTrendingReposUseCase(
    private val trendingRepoRepository: TrendingRepoRepository
) {
    fun execute(): Single<List<RepositoryDomainModel>> {
        return trendingRepoRepository.getTrendingRepos()
            .onErrorResumeNext { trendingRepoRepository.getTrendingReposFromDb() }
    }
}