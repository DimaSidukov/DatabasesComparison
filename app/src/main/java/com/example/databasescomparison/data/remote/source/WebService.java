package com.example.databasescomparison.data.remote.source;

import java.util.Objects;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WebService {

    private final OkHttpClient client;

    private static final String BASE_URL = "http://uoweb3.ncl.ac.uk/api/v1.1/";

    private static final String SENSORS = "sensors/json";

    public WebService(OkHttpClient cl) {
        this.client = cl;
    }

    private Request generateRequest() {
        HttpUrl.Builder urlBuilder = Objects
                .requireNonNull(HttpUrl.parse(BASE_URL + SENSORS))
                .newBuilder();
        String url = urlBuilder.build().toString();

        return new Request.Builder()
                .url(url)
                .build();
    }

    public void requestSensors(Callback callback) {
        client.newCall(generateRequest()).enqueue(callback);
    }

}
