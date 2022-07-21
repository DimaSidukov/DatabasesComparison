package com.example.databasescomparison.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.databasescomparison.R
import com.example.databasescomparison.data.model.timer.DbTimer
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainModel {

    private val presenter: MainPresenter by inject()

    private lateinit var addSensorsButton: Button

    private lateinit var getSensorsButton: Button
    private lateinit var deleteOneSensorButton: Button
    private lateinit var deleteSensorsButton: Button

    private lateinit var sqlohTime: TextView
    private lateinit var roomTime: TextView
    private lateinit var realmTime: TextView
    private lateinit var objectBoxTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) presenter.attachView(this)

        getSensorsButton = findViewById(R.id.get_sensors_button)
        deleteOneSensorButton = findViewById(R.id.delete_one_sensor_button)
        deleteSensorsButton = findViewById(R.id.delete_sensors_button)
        addSensorsButton = findViewById(R.id.add_sensors_button)

        sqlohTime = findViewById(R.id.sqloh_time)
        roomTime = findViewById(R.id.room_time)
        realmTime = findViewById(R.id.realm_time)
        objectBoxTime = findViewById(R.id.object_box_time)

        addSensorsButton.setOnClickListener {
            presenter.addSensors()
        }

        getSensorsButton.setOnClickListener {
            presenter.getSensors()
        }

        deleteOneSensorButton.setOnClickListener {
            presenter.deleteRandomSensor()
        }

        deleteSensorsButton.setOnClickListener {
            presenter.deleteSensors()
        }
    }

    override fun updateTimerResults(dbTimer: DbTimer) {
        runOnUiThread {
            sqlohTime.text = dbTimer.sqlohTime.toText()
            roomTime.text = dbTimer.roomTime.toText()
            realmTime.text = dbTimer.realmTime.toText()
            objectBoxTime.text = dbTimer.objectBoxTime.toText()
        }
    }

    private fun Long.toText() = "$this ms"
}