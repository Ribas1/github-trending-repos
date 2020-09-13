package com.pedroribeiro.trendingkotlinrepos

import android.app.Application
import com.pedroribeiro.data.di.databaseModule
import com.pedroribeiro.data.di.networkModule
import com.pedroribeiro.data.di.repositoryModule
import com.pedroribeiro.domain.di.useCaseModule
import com.pedroribeiro.trendingkotlinrepos.di.mapperModule
import com.pedroribeiro.trendingkotlinrepos.di.schedulersModule
import com.pedroribeiro.trendingkotlinrepos.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TrendingRepositoriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@TrendingRepositoriesApplication)
            modules(
                networkModule,
                viewModelModule,
                repositoryModule,
                mapperModule,
                schedulersModule,
                databaseModule,
                useCaseModule
            )
        }
    }
}