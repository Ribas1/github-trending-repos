package com.pedroribeiro.data.mappers

import com.pedroribeiro.data.models.BuiltByEntity
import com.pedroribeiro.data.models.TrendingRepositoriesEntity
import com.pedroribeiro.domain.models.BuiltByDomainModel
import com.pedroribeiro.domain.models.RepositoryDomainModel

class RepoEntityMapper {
    fun mapToEntity(reposDomainModel: List<RepositoryDomainModel>): List<TrendingRepositoriesEntity> {
        return reposDomainModel.map {
            mapRepoToEntity(it)
        }
    }

    private fun mapRepoToEntity(repositoryDomainModel: RepositoryDomainModel) =
        TrendingRepositoriesEntity(
            null,
            repositoryDomainModel.author,
            repositoryDomainModel.avatar,
            repositoryDomainModel.builtBy.map {
                mapToBuiltByEntity(it)
            },
            repositoryDomainModel.currentPeriodStars,
            repositoryDomainModel.description,
            repositoryDomainModel.forks,
            repositoryDomainModel.language,
            repositoryDomainModel.languageColor,
            repositoryDomainModel.name,
            repositoryDomainModel.stars,
            repositoryDomainModel.url
        )

    private fun mapToBuiltByEntity(builtByDomainModel: BuiltByDomainModel) = BuiltByEntity(
        null,
        builtByDomainModel.avatar,
        builtByDomainModel.href,
        builtByDomainModel.username
    )

}
