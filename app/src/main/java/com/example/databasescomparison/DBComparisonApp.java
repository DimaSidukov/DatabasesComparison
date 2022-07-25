package com.example.databasescomparison;

import static com.example.databasescomparison.di.ModulesKt.getAppModule;
import static com.example.databasescomparison.di.ModulesKt.getDatabaseModule;
import static com.example.databasescomparison.di.ModulesKt.getNetworkModule;
import static com.example.databasescomparison.di.ModulesKt.getRepositoryModule;
import static org.koin.core.context.DefaultContextExtKt.startKoin;

import android.app.Application;

import com.example.databasescomparison.di.ObjectBox;

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
                        getDatabaseModule(),
                        getRepositoryModule(),
                        getAppModule()
                );
        startKoin(koinApplication);

        ObjectBox.init(this);
    }
}
