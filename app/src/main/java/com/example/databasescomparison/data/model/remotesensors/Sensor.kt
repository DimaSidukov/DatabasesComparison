package com.example.databasescomparison.data.model.remotesensors

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sensor(
    @Expose
    @SerializedName("Broker Name")
    val brokerName: String?,

    @Expose
    @SerializedName("Ground Height Above Sea Level")
    val aboveSeaLevel: Double?,

    @Expose
    @SerializedName("Location (WKT)")
    val location: String?,

    @Expose
    @SerializedName("Raw ID")
    val rawID: String?,

    @Expose
    @SerializedName("Sensor Centroid Latitude")
    val latitude: Double?,

    @Expose
    @SerializedName("Sensor Centroid Longitude")
    val longitude: Double?,

    @Expose
    @SerializedName("Sensor Height Above Ground")
    val heightAboveGround: Double?,

    @Expose
    @SerializedName("sensorName")
    val sensorName: String?,

    @Expose
    @SerializedName("Third Party")
    val thirdParty: Boolean?
)