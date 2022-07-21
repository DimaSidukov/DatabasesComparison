package com.example.databasescomparison.data.model.realmsensor

import io.realm.kotlin.types.RealmObject

class RealmSensor() : RealmObject {

    constructor(
        brokerName: String?,
        aboveSeaLevel: Double?,
        location: String?,
        rawID: String?,
        latitude: Double?,
        longitude: Double?,
        heightAboveGround: Double?,
        sensorName: String?,
        thirdParty: Boolean?
    ) : this() {
        this.brokerName = brokerName
        this.aboveSeaLevel = aboveSeaLevel
        this.location = location
        this.rawID = rawID
        this.latitude = latitude
        this.longitude = longitude
        this.heightAboveGround = heightAboveGround
        this.sensorName = sensorName
        this.thirdParty = thirdParty
    }

    var brokerName: String? = null
    var aboveSeaLevel: Double? = null
    var location: String? = null
    var rawID: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var heightAboveGround: Double? = null
    var sensorName: String? = null
    var thirdParty: Boolean? = null
}