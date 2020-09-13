package com.pedroribeiro.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedroribeiro.data.models.TrendingRepositoriesEntity

@Dao
interface TrendingReposDao {
    @Query("SELECT * from repositories")
    fun getTrendingRepos(): List<TrendingRepositoriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRepos(repos: List<TrendingRepositoriesEntity>)
}