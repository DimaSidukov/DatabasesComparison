package com.example.databasescomparison.data.remote;

import java.util.Objects;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WebService {

    private final OkHttpClient client;

    private static final String API_KEY = "68f3eeff9f7149939f50e1e30b740151";
    private static final String BASE_URL = "https://newsapi.org/v2/";

    private static final String HEADLINES_PATH = "top-headlines";
    private static final String EVERYTHING_PATH = "everything";
    private static final String API_KEY_PARAMETER = "apiKey";
    private static final String COUNTRY_PARAMETER = "country";
    private static final String PAGE_SIZE_PARAMETER = "pageSize";
    private static final String PAGE_PARAMETER = "page";
    private static final String QUERY_PARAMETER = "q";

    public WebService(OkHttpClient client) {
        this.client = client;
    }

    private Request requestHeadlines(int page) {
        HttpUrl.Builder urlBuilder = Objects
                .requireNonNull(HttpUrl.parse(BASE_URL + HEADLINES_PATH))
                .newBuilder();
        urlBuilder.addQueryParameter(API_KEY_PARAMETER, API_KEY);
        urlBuilder.addQueryParameter(COUNTRY_PARAMETER, "us");
        urlBuilder.addQueryParameter(PAGE_SIZE_PARAMETER, "100");
        urlBuilder.addQueryParameter(PAGE_PARAMETER, String.valueOf(page));

        String url = urlBuilder.build().toString();

        return new Request.Builder()
                .url(url)
                .build();
    }

    private Request requestEverything(String query, int page) {
        HttpUrl.Builder urlBuilder = Objects
                .requireNonNull(HttpUrl.parse(BASE_URL + EVERYTHING_PATH))
                .newBuilder();
        urlBuilder.addQueryParameter(API_KEY_PARAMETER, API_KEY);
        urlBuilder.addQueryParameter(QUERY_PARAMETER, query);
        urlBuilder.addQueryParameter(PAGE_PARAMETER, String.valueOf(page));

        String url = urlBuilder.build().toString();

        return new Request.Builder()
                .url(url)
                .build();
    }

    public void requestNews(String query, Callback callback) {
        requestNews(query, 1, callback);
    }

    public void requestNews(String query, int page, Callback callback) {
        client.newCall(requestEverything(query, page)).enqueue(callback);
    }

    public void requestNews(Callback callback) {
        requestNews(1, callback);
    }

    public void requestNews(int page, Callback callback) {
        client.newCall(requestHeadlines(page)).enqueue(callback);
    }


}
