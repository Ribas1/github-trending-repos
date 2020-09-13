package com.pedroribeiro.data.di

import android.content.Context
import androidx.room.Room
import com.pedroribeiro.data.db.TrendingReposDao
import com.pedroribeiro.data.db.TrendingReposDatabase
import com.pedroribeiro.data.db.TrendingReposDatabase.Companion.DATABASE_NAME
import org.koin.dsl.module

val databaseModule = module {
    single { provideTrendingReposDb(get()) }
    single { provideTrendingReposDao(get()) }
}

private fun provideTrendingReposDb(context: Context): TrendingReposDatabase {
    return Room.databaseBuilder(context, TrendingReposDatabase::class.java, DATABASE_NAME)
        .build()
}

private fun provideTrendingReposDao(database: TrendingReposDatabase): TrendingReposDao {
    return database.repoDao()
}
