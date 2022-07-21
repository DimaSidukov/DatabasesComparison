package com.example.databasescomparison.data.local.source.room

import com.example.databasescomparison.data.model.remotesensors.Sensor
import com.example.databasescomparison.data.model.roomsensor.toRoomSensor

class RoomDaoHelper(private val roomSensorDao: RoomSensorDao) {

    fun getRoomSensors() = roomSensorDao.getRoomSensors()

    suspend fun insertRoomSensor(sensor: Sensor) =
        roomSensorDao.insertRoomSensor(sensor.toRoomSensor())

    suspend fun insertRoomSensors(sensors: List<Sensor>) =
        roomSensorDao.insertRoomSensors(sensors.map { it.toRoomSensor() })

    suspend fun updateRoomSensor(sensor: Sensor) =
        roomSensorDao.updateRoomSensor(sensor.toRoomSensor())

    suspend fun deleteRoomSensor(sensor: Sensor) =
        roomSensorDao.deleteRoomSensor(sensor.toRoomSensor())

    suspend fun deleteAllRoomSensors() = roomSensorDao.deleteAllRoomSensors()

}