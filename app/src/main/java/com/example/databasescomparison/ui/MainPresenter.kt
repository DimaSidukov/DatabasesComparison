package com.example.databasescomparison.ui

import android.util.Log
import com.example.databasescomparison.data.remote.WebService
import com.example.databasescomparison.data.remote.model.NewsBody
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainPresenter(private val webService: WebService) {

    private lateinit var model: MainModel
    private val gson = Gson()

    fun attachView(model: MainModel) {
        this.model = model
        requestHeadliners()
    }

    private fun requestHeadliners() {
        webService.requestNews(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("WEB_SERVICE_ERROR", e.message ?: "unknown")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = gson.fromJson(response.body?.charStream(), NewsBody::class.java)
                model.showNewsList(result)
            }
        })
    }

    fun requestByQuery(query: String) {
        webService.requestNews(query, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("WEB_SERVICE_ERROR", e.message ?: "unknown")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = gson.fromJson(response.body?.charStream(), NewsBody::class.java)
                model.showNewsList(result)
            }
        })
    }
}