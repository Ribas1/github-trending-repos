package com.pedroribeiro.trendingkotlinrepos.di

import com.pedroribeiro.trendingkotlinrepos.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel(get()) }
}