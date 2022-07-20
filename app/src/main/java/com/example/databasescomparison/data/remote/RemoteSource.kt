package com.example.databasescomparison.data.remote

import android.util.Log
import com.example.databasescomparison.data.model.remotenews.Article
import com.example.databasescomparison.data.model.remotenews.NewsBody
import com.example.databasescomparison.data.remote.source.WebService
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class RemoteSource(private val webService: WebService, private val gson: Gson) {

    fun requestHeadlines(onSuccess: (List<Article>) -> Unit) =
        webService.requestNews(callback(onSuccess))

    fun requestHeadlinesOnPage(page: Int, onSuccess: (List<Article>) -> Unit) =
        webService.requestNews(page, callback(onSuccess))


    fun requestNewsByQuery(query: String, onSuccess: (List<Article>) -> Unit) =
        webService.requestNews(query, callback(onSuccess))

    fun requestNewsByQueryOnPage(query: String, page: Int, onSuccess: (List<Article>) -> Unit) =
        webService.requestNews(query, page, callback(onSuccess))

    private fun callback(onSuccess: (List<Article>) -> Unit) = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            logError(e)
        }

        override fun onResponse(call: Call, response: Response) {
            onSuccess(gson.fromJson(response.body?.charStream(), NewsBody::class.java).articles)
        }
    }

    private fun logError(e: Exception) {
        Log.d("WebService error", e.message ?: "unknown")
    }

}