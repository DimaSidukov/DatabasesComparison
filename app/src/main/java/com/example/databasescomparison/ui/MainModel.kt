package com.example.databasescomparison.ui

import com.example.databasescomparison.data.model.timer.DbTimer

interface MainModel {
    fun updateTimerResults(dbTimer: DbTimer)
}