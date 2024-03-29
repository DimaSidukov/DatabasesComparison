package com.example.databasescomparison.data

import com.example.databasescomparison.data.local.LocalSource
import com.example.databasescomparison.data.model.remotesensor.Sensor
import com.example.databasescomparison.data.remote.RemoteSource

class Repository(private val localSource: LocalSource, private val remoteSource: RemoteSource) {

    fun requestSensors(onSuccess: (List<Sensor>) -> Unit) =
        remoteSource.requestSensors(onSuccess)

    suspend fun addSensors(sensors: List<Sensor>) = localSource.addSensors(sensors)

    suspend fun getSensors() = localSource.getSensors()

    suspend fun deleteAllSensors() = localSource.deleteAllSensors()

}