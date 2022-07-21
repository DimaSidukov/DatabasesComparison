package com.example.databasescomparison.data.local

import com.example.databasescomparison.data.local.source.objectbox.ObjectBoxHandler
import com.example.databasescomparison.data.local.source.realm.RealmHelper
import com.example.databasescomparison.data.local.source.room.RoomDaoHelper
import com.example.databasescomparison.data.local.source.sqloh.SQLOHDatabaseHelper
import com.example.databasescomparison.data.model.remotesensor.Sensor
import com.example.databasescomparison.data.model.timer.DbTimer
import kotlinx.coroutines.*

class LocalSource(
    private val sqloh: SQLOHDatabaseHelper,
    private val roomDaoHelper: RoomDaoHelper,
    private val realmHelper: RealmHelper,
    private val objectBox: ObjectBoxHandler
) {

    private val scope by lazy { CoroutineScope(Job() + Dispatchers.IO) }

    suspend fun addSensor(sensor: Sensor) = createDbTimer(
        { sqloh.addSensor(sensor) },
        { roomDaoHelper.insertRoomSensor(sensor) },
        { realmHelper.addRealmSensor(sensor) },
        { objectBox.addObjectBoxSensor(sensor) }
    )

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

    suspend fun deleteSensor(sensor: Sensor) = createDbTimer(
        { sqloh.deleteSensor(sensor) },
        { roomDaoHelper.deleteRoomSensor(sensor) },
        { realmHelper.deleteRealmSensor(sensor) },
        { objectBox.deleteObjectBoxSensor(sensor) }
    )

    suspend fun deleteAllSensors() = createDbTimer(
        { sqloh.deleteAllSensors() },
        { roomDaoHelper.deleteAllRoomSensors() },
        { realmHelper.deleteRealmSensors() },
        { objectBox.deleteObjectBoxSensors() }
    )

    suspend fun updateSensor(sensor: Sensor) = createDbTimer(
        { sqloh.updateSensor(sensor) },
        { roomDaoHelper.updateRoomSensor(sensor) },
        { realmHelper.updateRealmSensor(sensor) },
        { objectBox.updateObjectBoxSensor(sensor) }
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