package com.pedroribeiro.trendingkotlinrepos.schedulers

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class TrampolineSchedulerProviderImpl : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
}