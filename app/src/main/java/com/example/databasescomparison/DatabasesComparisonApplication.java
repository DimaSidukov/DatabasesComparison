package com.example.databasescomparison;

import static com.example.databasescomparison.di.ModulesKt.getAppModule;
import static com.example.databasescomparison.di.ModulesKt.getNetworkModule;
import static org.koin.core.context.DefaultContextExtKt.startKoin;

import android.app.Application;

import org.koin.android.java.KoinAndroidApplication;
import org.koin.core.KoinApplication;

public class DatabasesComparisonApplication extends Application {

    public DatabasesComparisonApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        KoinApplication koinApplication = KoinAndroidApplication
                .create(this)
                .modules(
                        getNetworkModule(),
                        getAppModule()
                );
        startKoin(koinApplication);
    }

}
