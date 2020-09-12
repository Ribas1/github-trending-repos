package com.pedroribeiro.trendingkotlinrepos.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private fun Disposable.addToCompositeDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun <T> Single<T>.baseSubscribe(
        subscribeOn: Scheduler? = Schedulers.io(),
        observeOn: Scheduler? = AndroidSchedulers.mainThread(),
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: (T) -> Unit
    ) {
        this.subscribeOn(subscribeOn)
            .run {
                if (observeOn != null) {
                    observeOn(observeOn)
                } else {
                    this
                }
            }
            .subscribe(
                { onSuccess.invoke(it) },
                { onError?.invoke(it) }
            ).addToCompositeDisposable()
    }
}
