package com.pedroribeiro.trendingkotlinrepos.details

import androidx.lifecycle.LiveData
import com.pedroribeiro.trendingkotlinrepos.common.BaseViewModel
import com.pedroribeiro.trendingkotlinrepos.common.SingleLiveEvent
import com.pedroribeiro.trendingkotlinrepos.schedulers.SchedulerProvider

class DetailsViewModel(
    schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    fun onUpClick() {
        _navigation.postValue(Navigation.Up)
    }

    sealed class Navigation {
        object Up : Navigation()
    }
}
