package com.example.databasescomparison.data.local.source

import com.example.databasescomparison.data.model.RealmSensor
import com.example.databasescomparison.data.model.remotesensor.Sensor
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RealmHelper(private val realm: Realm) {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun getRealmSensors() = realm.query<RealmSensor>().find()

    suspend fun addRealmSensor(sensor: Sensor) {
        scope.launch {
            realm.write {
                this.query<RealmSensor>("location == \"${sensor.location}\"")
                    .first()
                    .find()
                    ?.let {
                        this.copyToRealm(
                            RealmSensor().apply {
                                brokerName = sensor.brokerName
                                aboveSeaLevel = sensor.aboveSeaLevel
                                location = sensor.location
                                rawID = sensor.rawID
                                latitude = sensor.latitude
                                longitude = sensor.longitude
                                heightAboveGround = sensor.heightAboveGround
                                sensorName = sensor.sensorName
                                thirdParty = sensor.thirdParty
                            }
                        )
                    }
            }
        }
    }

    suspend fun addRealmSensors(sensors: List<Sensor>) = sensors.forEach { addRealmSensor(it) }

    suspend fun updateRealmSensor(sensor: Sensor) = realm.write {
        val sensorInDb = this.query<RealmSensor>("location == ${sensor.location}").first().find()
        sensorInDb?.brokerName = sensor.brokerName
        sensorInDb?.aboveSeaLevel = sensor.aboveSeaLevel
        sensorInDb?.location = sensor.location
        sensorInDb?.rawID = sensor.rawID
        sensorInDb?.latitude = sensor.latitude
        sensorInDb?.longitude = sensor.longitude
        sensorInDb?.heightAboveGround = sensor.heightAboveGround
        sensorInDb?.sensorName = sensor.sensorName
        sensorInDb?.thirdParty = sensor.thirdParty
    }

    suspend fun deleteRealmSensor(sensor: Sensor) = realm.write {
        val sensorInDb = this.query<RealmSensor>("location == ${sensor.location}").find()
        delete(sensorInDb.first())
    }

    suspend fun deleteRealmSensors() = realm.write {
        delete(query<RealmSensor>().find())
    }

}