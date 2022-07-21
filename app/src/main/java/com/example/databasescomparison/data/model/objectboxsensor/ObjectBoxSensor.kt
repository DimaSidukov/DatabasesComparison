package com.example.databasescomparison.data.model.objectboxsensor

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ObjectBoxSensor(
    @Id
    var id: Long = 0,
    var brokerName: String? = null,
    var aboveSeaLevel: Double? = null,
    var location: String? = null,
    var rawID: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var heightAboveGround: Double? = null,
    var sensorName: String? = null,
    var thirdParty: Boolean? = null
)