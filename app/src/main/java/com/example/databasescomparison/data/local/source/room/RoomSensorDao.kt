package com.example.databasescomparison.data.local.source.room

import androidx.room.*
import com.example.databasescomparison.data.model.RoomSensor

@Dao
interface RoomSensorDao {

    @Query("SELECT * FROM RoomSensor")
    fun getRoomSensors(): List<RoomSensor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomSensor(sensor: RoomSensor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomSensors(sensors: List<RoomSensor>)

    @Update
    suspend fun updateRoomSensor(sensor: RoomSensor)

    @Delete
    suspend fun deleteRoomSensor(sensor: RoomSensor)

    @Query("DELETE FROM RoomSensor")
    suspend fun deleteAllRoomSensors()

}