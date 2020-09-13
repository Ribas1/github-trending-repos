package com.pedroribeiro.domain.di

import com.pedroribeiro.domain.usecases.GetTrendingReposUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetTrendingReposUseCase(get()) }
}