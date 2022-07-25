package com.example.databasescomparison.data.local

import com.example.databasescomparison.data.local.source.ObjectBoxHandler
import com.example.databasescomparison.data.local.source.RealmHelper
import com.example.databasescomparison.data.local.source.room.RoomDaoHelper
import com.example.databasescomparison.data.local.source.SQLOHDatabaseHelper
import com.example.databasescomparison.data.model.remotesensor.Sensor
import com.example.databasescomparison.data.model.DbTimer
import kotlinx.coroutines.*

class LocalSource(
    private val sqloh: SQLOHDatabaseHelper,
    private val roomDaoHelper: RoomDaoHelper,
    private val realmHelper: RealmHelper,
    private val objectBox: ObjectBoxHandler
) {

    private val scope by lazy { CoroutineScope(Job() + Dispatchers.IO) }

    suspend fun addSensors(sensors: List<Sensor>) = createDbTimer(
        { sqloh.addSensors(sensors) },
        { roomDaoHelper.insertRoomSensors(sensors) },
        { realmHelper.addRealmSensors(sensors) },
        { objectBox.addObjectBoxSensors(sensors) }
    )

    suspend fun getSensors() = createDbTimer(
        { sqloh.sensors },
        { roomDaoHelper.getRoomSensors() },
        { realmHelper.getRealmSensors() },
        { objectBox.getObjectBoxSensors() }
    )

    suspend fun deleteAllSensors() = createDbTimer(
        { sqloh.deleteAllSensors() },
        { roomDaoHelper.deleteAllRoomSensors() },
        { realmHelper.deleteRealmSensors() },
        { objectBox.deleteObjectBoxSensors() }
    )

    private suspend fun timeMethod(func: suspend () -> Unit): Long =
        withContext(scope.coroutineContext) {
            val begin = System.currentTimeMillis()
            func()
            val end = System.currentTimeMillis()
            end - begin
        }

    private suspend fun createDbTimer(
        sqlohFunc: suspend () -> Unit,
        roomFunc: suspend () -> Unit,
        realmFunc: suspend () -> Unit,
        objectBoxFunc: suspend () -> Unit
    ): DbTimer =
        withContext(scope.coroutineContext) {
            val dbTimer = DbTimer()
            dbTimer.sqlohTime = timeMethod(sqlohFunc)
            dbTimer.roomTime = timeMethod(roomFunc)
            dbTimer.realmTime = timeMethod(realmFunc)
            dbTimer.objectBoxTime = timeMethod(objectBoxFunc)
            dbTimer
        }
}