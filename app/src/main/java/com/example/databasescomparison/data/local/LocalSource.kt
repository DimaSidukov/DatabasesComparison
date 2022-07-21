package com.example.databasescomparison.data.local

import com.example.databasescomparison.data.local.source.room.RoomDaoHelper
import com.example.databasescomparison.data.local.source.sqloh.SQLOHDatabaseHelper
import com.example.databasescomparison.data.model.remotesensors.Sensor
import com.example.databasescomparison.data.model.timer.DbTimer
import kotlinx.coroutines.*

class LocalSource(
    private val sqloh: SQLOHDatabaseHelper,
    private val roomDaoHelper: RoomDaoHelper,
) {

    private val scope by lazy { CoroutineScope(Job() + Dispatchers.IO) }

    suspend fun addSensor(sensor: Sensor) = createDbTimer(
        { sqloh.addSensor(sensor) },
        { roomDaoHelper.insertRoomSensor(sensor) },
        {}, {}
    )

    suspend fun addSensors(sensors: List<Sensor>) = createDbTimer(
        { sqloh.addSensors(sensors) },
        { roomDaoHelper.insertRoomSensors(sensors) },
        {}, {}
    )

    suspend fun getSensors() = createDbTimer(
        { sqloh.sensors },
        { roomDaoHelper.getRoomSensors() },
        {}, {}
    )

    suspend fun deleteSensor(sensor: Sensor) = createDbTimer(
        { sqloh.deleteSensor(sensor) },
        { roomDaoHelper.deleteRoomSensor(sensor) },
        {}, {}
    )

    suspend fun deleteAllSensors() = createDbTimer(
        { sqloh.deleteAllSensors() },
        { roomDaoHelper.deleteAllRoomSensors() },
        {}, {}
    )

    suspend fun updateSensor(sensor: Sensor) = createDbTimer(
        { sqloh.updateSensor(sensor) },
        { roomDaoHelper.updateRoomSensor(sensor) },
        {}, {}
    )


    private suspend fun timeMethod(func: suspend () -> Unit): Deferred<Long> = scope.async {
        val begin = System.currentTimeMillis()
        scope.launch { func() }
        val end = System.currentTimeMillis()
        end - begin
    }

    private suspend fun createDbTimer(
        sqlohFunc: () -> Unit,
        roomFunc: suspend () -> Unit,
        realmFunc: () -> Unit,
        objectBoxFunc: () -> Unit
    ): DbTimer =
        withContext(scope.coroutineContext) {
            val dbTimer = DbTimer()
            dbTimer.sqlohTime = timeMethod(sqlohFunc).await()
            dbTimer.roomTime = timeMethod(roomFunc).await()
            dbTimer.realmTime = timeMethod(realmFunc).await()
            dbTimer.objectBoxTime = timeMethod(objectBoxFunc).await()
            dbTimer
        }

}