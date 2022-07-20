package com.example.databasescomparison;

import static com.example.databasescomparison.di.ModulesKt.getAppModule;
import static com.example.databasescomparison.di.ModulesKt.getNetworkModule;
import static com.example.databasescomparison.di.ModulesKt.getRepositoryModule;
import static org.koin.core.context.DefaultContextExtKt.startKoin;

import android.app.Application;

import org.koin.android.java.KoinAndroidApplication;
import org.koin.core.KoinApplication;

public class DBComparisonApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KoinApplication koinApplication = KoinAndroidApplication
            .create(this)
            .modules(
                getNetworkModule(),
                getRepositoryModule(),
                getAppModule()
            );
        startKoin(koinApplication);
    }

}
