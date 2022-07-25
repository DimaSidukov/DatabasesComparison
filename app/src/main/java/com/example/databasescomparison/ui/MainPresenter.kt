package com.example.databasescomparison.ui

import com.example.databasescomparison.data.Repository
import com.example.databasescomparison.data.model.DbTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainPresenter(private val repository: Repository) {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    private lateinit var model: MainModel

    fun attachView(model: MainModel) {
        this.model = model
    }

    fun addSensors() {
        repository.requestSensors { list ->
            showTimeResults {
                repository.addSensors(list)
            }
        }
    }

    fun getSensors() {
        showTimeResults {
            repository.getSensors()
        }
    }

    fun deleteRandomSensor() {}

    fun deleteSensors() {
        showTimeResults {
            repository.deleteAllSensors()
        }
    }

    private fun showTimeResults(func: suspend () -> DbTimer) {
        scope.launch {
            model.updateTimerResults(func())
        }
    }
}