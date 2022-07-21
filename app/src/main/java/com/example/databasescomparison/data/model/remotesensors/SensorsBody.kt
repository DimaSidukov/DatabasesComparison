package com.example.databasescomparison.data.model.remotesensors

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SensorsBody(
    @Expose
    @SerializedName("sensors")
    val sensors: List<Sensor>?
)