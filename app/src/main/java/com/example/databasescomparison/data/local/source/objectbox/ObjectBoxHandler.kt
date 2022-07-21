package com.example.databasescomparison.data.local.source.objectbox

import com.example.databasescomparison.data.model.objectboxsensor.ObjectBoxSensor
import com.example.databasescomparison.data.model.objectboxsensor.ObjectBoxSensor_
import com.example.databasescomparison.data.model.remotesensor.Sensor
import com.example.databasescomparison.di.ObjectBox

class ObjectBoxHandler {

    val sensorBox = ObjectBox.get().boxFor(ObjectBoxSensor::class.java)

    fun addObjectBoxSensor(sensor: Sensor) = sensorBox.put(sensor.toObjectBoxSensor())

    fun addObjectBoxSensors(sensors: List<Sensor>) =
        sensorBox.put(sensors.map { it.toObjectBoxSensor() })

    fun getObjectBoxSensors() = sensorBox.all

    fun updateObjectBoxSensor(sensor: Sensor) {
        sensor.location?.let { location ->
            val objectId = sensorBox.query(ObjectBoxSensor_.location.equal(location))
                .build()
                .findFirst()
                ?.id

            objectId?.let {
                var objectInBox = sensorBox.get(it)
                objectInBox = sensor.toObjectBoxSensor()
                sensorBox.put(objectInBox)
            }
        }
    }

    fun deleteObjectBoxSensor(sensor: Sensor) = sensorBox.remove(sensor.toObjectBoxSensor())

    fun deleteObjectBoxSensors() = sensorBox.removeAll()

    private fun Sensor.toObjectBoxSensor() = ObjectBoxSensor(
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

}