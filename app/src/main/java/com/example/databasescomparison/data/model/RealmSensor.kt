package com.example.databasescomparison.data.model

import io.realm.kotlin.types.RealmObject

class RealmSensor : RealmObject {

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