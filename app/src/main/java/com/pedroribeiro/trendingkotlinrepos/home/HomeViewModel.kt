package com.pedroribeiro.trendingkotlinrepos.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pedroribeiro.data.network.exceptions.EmptyDatabaseException
import com.pedroribeiro.domain.repositories.TrendingRepoRepository
import com.pedroribeiro.domain.usecases.GetTrendingReposUseCase
import com.pedroribeiro.trendingkotlinrepos.common.BaseViewModel
import com.pedroribeiro.trendingkotlinrepos.common.SingleLiveEvent
import com.pedroribeiro.trendingkotlinrepos.mappers.RepositoryModelMapper
import com.pedroribeiro.trendingkotlinrepos.models.RepositoryUiModel
import com.pedroribeiro.trendingkotlinrepos.schedulers.SchedulerProvider

class HomeViewModel(
    private val useCase: GetTrendingReposUseCase,
    private val repositoryModelMapper: RepositoryModelMapper,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _repositories = MutableLiveData<List<RepositoryUiModel>>()
    val repositories: LiveData<List<RepositoryUiModel>> = _repositories

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error> = _error

    fun getTrendingRepos() {
        useCase.execute()
            .doOnSubscribe { _loading.postValue(true) }
            .doFinally { _loading.postValue(false) }
            .map { repositoryModelMapper.mapToUi(it) }
            .baseSubscribe(
                onSuccess = ::onGetRepositoriesSuccess,
                onError = ::onGetRepositoriesFailed
            )
    }

    fun onRepositoryClick(repo: RepositoryUiModel) {
        _navigation.postValue(Navigation.ToRepoDetails(repo))
    }

    private fun onGetRepositoriesSuccess(list: List<RepositoryUiModel>) {
        _repositories.postValue(list)
    }

    private fun onGetRepositoriesFailed(throwable: Throwable) {
        if (throwable is EmptyDatabaseException) {
            _error.postValue(Error.EmptyDatabase)
        } else {
            _error.postValue(Error.Generic)
        }
    }

    sealed class Navigation {
        data class ToRepoDetails(
            val repo: RepositoryUiModel
        ) : Navigation()
    }

    sealed class Error {
        object EmptyDatabase : Error()
        object Generic : Error()
    }
}
