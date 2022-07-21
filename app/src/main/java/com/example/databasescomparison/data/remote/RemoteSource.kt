package com.example.databasescomparison.data.remote

import android.util.Log
import com.example.databasescomparison.data.model.remotesensors.Sensor
import com.example.databasescomparison.data.model.remotesensors.SensorsBody
import com.example.databasescomparison.data.remote.source.WebService
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class RemoteSource(private val webService: WebService, private val gson: Gson) {

    fun requestSensors(onSuccess: (List<Sensor>) -> Unit) =
        webService.requestSensors(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("WebService error", e.message ?: "unknown")
            }

            override fun onResponse(call: Call, response: Response) {
                gson.fromJson(response.body?.charStream(), SensorsBody::class.java)?.let {
                    onSuccess(it.sensors ?: emptyList())
                }
            }
        })
}