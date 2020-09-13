package com.pedroribeiro.trendingkotlinrepos.di

import com.pedroribeiro.trendingkotlinrepos.schedulers.SchedulerProvider
import com.pedroribeiro.trendingkotlinrepos.schedulers.SchedulerProviderImpl
import org.koin.dsl.module

val schedulersModule = module {
    single<SchedulerProvider>{ SchedulerProviderImpl() }
}