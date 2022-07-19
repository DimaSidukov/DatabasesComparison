package com.example.databasescomparison.di

import com.example.databasescomparison.data.remote.WebService
import com.example.databasescomparison.ui.MainPresenter
import okhttp3.OkHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideWebService(get()) }
}

val appModule = module {

    factory { MainPresenter(get()) }

}

fun provideOkHttpClient() = OkHttpClient().newBuilder().build()

fun provideWebService(okHttpClient: OkHttpClient) = WebService(okHttpClient)