package com.example.databasescomparison.di

import android.content.Context
import androidx.room.Room
import com.example.databasescomparison.data.Repository
import com.example.databasescomparison.data.local.LocalSource
import com.example.databasescomparison.data.local.source.room.RoomDaoHelper
import com.example.databasescomparison.data.local.source.room.RoomSensorsDatabase
import com.example.databasescomparison.data.local.source.sqloh.SQLOHDatabaseHelper
import com.example.databasescomparison.data.remote.RemoteSource
import com.example.databasescomparison.data.remote.source.WebService
import com.example.databasescomparison.ui.MainPresenter
import com.google.gson.Gson
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.security.KeyPair
import java.security.KeyStore
import java.security.PrivateKey
import java.security.cert.X509Certificate

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideWebService(get()) }
}

val databaseModule = module {
    single { provideSQLOH(androidContext()) }
    single { provideRoomDatabase(androidContext()) }
    single { provideRoomDaoHelper(get()) }
}

val repositoryModule = module {
    single { provideLocalSource(get(), get()) }
    single { provideRemoteSource(get()) }
    single { provideRepository(get(), get()) }
}

val appModule = module {
    factory { MainPresenter(get()) }
}

/**
 *  Network providers
 */
fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()

    builder.connectionSpecs(
        listOf(
            ConnectionSpec.CLEARTEXT,
            ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .allEnabledTlsVersions()
                .allEnabledCipherSuites()
                .build()
        )
    )

    return builder.build()
}

fun provideWebService(okHttpClient: OkHttpClient) =
    WebService(okHttpClient)

/**
 *  Database providers
 */
fun provideSQLOH(context: Context): SQLOHDatabaseHelper = SQLOHDatabaseHelper.getInstance(context)

fun provideRoomDatabase(context: Context) = Room.databaseBuilder(
    context,
    RoomSensorsDatabase::class.java, "room-database"
).build()

fun provideRoomDaoHelper(roomSensorsDatabase: RoomSensorsDatabase) =
    RoomDaoHelper(roomSensorsDatabase.roomSensorDao())

/**
 *  Repository providers
 */
fun provideRepository(localSource: LocalSource, remoteSource: RemoteSource) =
    Repository(localSource, remoteSource)

fun provideRemoteSource(webService: WebService) = RemoteSource(webService, Gson())

fun provideLocalSource(sqloh: SQLOHDatabaseHelper, room: RoomDaoHelper) = LocalSource(
    sqloh, room
)
