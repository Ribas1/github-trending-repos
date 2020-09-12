package com.pedroribeiro.trendingkotlinrepos.di

import com.pedroribeiro.trendingkotlinrepos.mappers.RepositoryModelMapper
import org.koin.dsl.module

val mapperModule = module {
    single { RepositoryModelMapper() }
}