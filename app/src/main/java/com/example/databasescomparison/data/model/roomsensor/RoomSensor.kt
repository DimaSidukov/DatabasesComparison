package com.example.databasescomparison.data.model.roomsensor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.databasescomparison.data.model.remotesensors.Sensor

@Entity
data class RoomSensor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("brokerName")
    val brokerName: String?,
    @ColumnInfo("aboveSeaLevel")
    val aboveSeaLevel: Double?,
    @ColumnInfo("location")
    val location: String?,
    @ColumnInfo("rawID")
    val rawID: String?,
    @ColumnInfo("latitude")
    val latitude: Double?,
    @ColumnInfo("longitude")
    val longitude: Double?,
    @ColumnInfo("heightAboveGround")
    val heightAboveGround: Double?,
    @ColumnInfo("sensorName")
    val sensorName: String?,
    @ColumnInfo("thirdParty")
    val thirdParty: Boolean?
)

fun Sensor.toRoomSensor() = RoomSensor(
    brokerName = this.brokerName,
    aboveSeaLevel = this.aboveSeaLevel,
    location = this.location,
    rawID = this.rawID,
    latitude = this.latitude,
    longitude = this.longitude,
    heightAboveGround = this.heightAboveGround,
    sensorName = this.sensorName,
    thirdParty = this.thirdParty
)
