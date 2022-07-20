package com.example.databasescomparison.di

import android.content.Context
import com.example.databasescomparison.data.Repository
import com.example.databasescomparison.data.local.LocalSource
import com.example.databasescomparison.data.local.source.sqloh.SQLOHDatabaseHelper
import com.example.databasescomparison.data.remote.RemoteSource
import com.example.databasescomparison.data.remote.source.WebService
import com.example.databasescomparison.ui.MainPresenter
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideWebService(get()) }
}

val repositoryModule = module {
    single { provideLocalSource(androidContext()) }
    single { provideRemoteSource(get()) }
    single { provideRepository(get(), get()) }
}

val appModule = module {
    factory { MainPresenter(get()) }
}

fun provideOkHttpClient() = OkHttpClient().newBuilder().build()

fun provideWebService(okHttpClient: OkHttpClient) =
    WebService(okHttpClient)

fun provideRepository(localSource: LocalSource, remoteSource: RemoteSource) =
    Repository(localSource, remoteSource)

fun provideLocalSource(context: Context) = LocalSource(
    provideSQLOH(context)
)

fun provideRemoteSource(webService: WebService) = RemoteSource(webService, Gson())

fun provideSQLOH(context: Context): SQLOHDatabaseHelper = SQLOHDatabaseHelper.getInstance(context)