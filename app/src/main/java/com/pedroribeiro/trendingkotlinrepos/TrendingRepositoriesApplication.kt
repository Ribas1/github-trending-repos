package com.pedroribeiro.trendingkotlinrepos

import android.app.Application
import com.pedroribeiro.data.di.networkModule
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
                networkModule
            )
        }
    }
}