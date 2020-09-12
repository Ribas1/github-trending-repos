package com.pedroribeiro.trendingkotlinrepos.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedroribeiro.domain.TrendingRepoRepository
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.trendingkotlinrepos.common.BaseViewModel

class HomeViewModel(
    private val repository: TrendingRepoRepository
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<RepositoryDomainModel>>()
    val repositories: LiveData<List<RepositoryDomainModel>> = _repositories

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    fun getTrendingRepos() {
        repository.getTrendingRepositories()
            .baseSubscribe(
                onSuccess = ::onGetRepositoriesSuccess,
                onError = ::onGetRepositoriesFailed
            )
    }

    private fun onGetRepositoriesSuccess(list: List<RepositoryDomainModel>) {
        _repositories.postValue(list)
    }

    private fun onGetRepositoriesFailed(throwable: Throwable) {
        _error.postValue(Unit)
    }

}
