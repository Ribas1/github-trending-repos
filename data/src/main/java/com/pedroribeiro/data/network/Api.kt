package com.pedroribeiro.data.network

import com.pedroribeiro.data.models.TrendingRepositoriesEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("repositories")
    fun getTrendingRepositories(
        @Query("language") language: String,
        @Query("since") since: String
    ): Single<List<TrendingRepositoriesEntity>>
}