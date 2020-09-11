package com.pedroribeiro.data.di

import com.pedroribeiro.data.BuildConfig.BASE_URL
import com.pedroribeiro.data.network.Api
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideOkHttClient() }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
}

fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideOkHttClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .build()
}
