package com.pedroribeiro.data.db

import androidx.room.*
import com.pedroribeiro.data.models.TrendingRepositoriesEntity

@Database(
    entities = [TrendingRepositoriesEntity::class],
    version = 1
)
@TypeConverters(DBConverters::class)
abstract class TrendingReposDatabase : RoomDatabase() {
    abstract fun repoDao(): TrendingReposDao

    companion object {
        const val DATABASE_NAME = "trending_repos_db"
    }
}