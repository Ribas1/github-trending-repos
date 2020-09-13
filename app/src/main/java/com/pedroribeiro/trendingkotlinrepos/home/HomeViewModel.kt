package com.pedroribeiro.trendingkotlinrepos.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedroribeiro.domain.TrendingRepoRepository
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.trendingkotlinrepos.common.BaseViewModel
import com.pedroribeiro.trendingkotlinrepos.common.SingleLiveEvent
import com.pedroribeiro.trendingkotlinrepos.mappers.RepositoryModelMapper
import com.pedroribeiro.trendingkotlinrepos.models.RepositoryUiModel

class HomeViewModel(
    private val repository: TrendingRepoRepository,
    private val repositoryModelMapper: RepositoryModelMapper
) : BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _repositories = MutableLiveData<List<RepositoryUiModel>>()
    val repositories: LiveData<List<RepositoryUiModel>> = _repositories

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    fun getTrendingRepos() {
        repository.getTrendingRepositories()
            .doOnSubscribe { _loading.postValue(true) }
            .doOnSubscribe { _loading.postValue(false) }
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
        _error.postValue(Unit)
    }

    sealed class Navigation {
        data class ToRepoDetails(
            val repo: RepositoryUiModel
        ) : Navigation()
    }
}
