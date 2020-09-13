package com.pedroribeiro.data.di

import com.pedroribeiro.domain.repositories.TrendingRepoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TrendingRepoRepository> { TrendigRepoRepositoryImpl(get(), get()) }
}