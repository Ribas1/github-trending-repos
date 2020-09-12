package com.pedroribeiro.trendingkotlinrepos.mappers

import com.pedroribeiro.domain.models.BuiltByDomainModel
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.trendingkotlinrepos.models.BuiltByUiModel
import com.pedroribeiro.trendingkotlinrepos.models.RepositoryUiModel

class RepositoryModelMapper {
    fun mapToUi(it: List<RepositoryDomainModel>): List<RepositoryUiModel> {
        return it.map { repositoryDomainModel ->
            mapRepoToUi(repositoryDomainModel)
        }
    }

    private fun mapRepoToUi(repositoryDomainModel: RepositoryDomainModel) = RepositoryUiModel(
        repositoryDomainModel.author,
        repositoryDomainModel.avatar,
        repositoryDomainModel.builtBy.map { mapBuiltByToUi(it) },
        repositoryDomainModel.currentPeriodStars,
        repositoryDomainModel.description,
        repositoryDomainModel.forks,
        repositoryDomainModel.language,
        repositoryDomainModel.languageColor,
        repositoryDomainModel.name,
        repositoryDomainModel.stars,
        repositoryDomainModel.url
    )

    private fun mapBuiltByToUi(builtByDomainModel: BuiltByDomainModel) = BuiltByUiModel(
        builtByDomainModel.avatar,
        builtByDomainModel.username
    )

}
