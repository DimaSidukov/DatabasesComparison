package com.example.databasescomparison.data.local.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.databasescomparison.data.model.RoomSensor

@Database(entities = [RoomSensor::class], version = 1)
abstract class RoomSensorsDatabase : RoomDatabase() {
    abstract fun roomSensorDao(): RoomSensorDao
}