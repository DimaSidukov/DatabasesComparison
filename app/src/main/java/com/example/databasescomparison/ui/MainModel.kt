package com.example.databasescomparison.ui

import com.example.databasescomparison.data.model.remotenews.Article
import com.example.databasescomparison.data.model.timer.DbTimer

interface MainModel {
    fun showNewsList(data: List<Article>)
    fun updateTimerResults(dbTimer: DbTimer)
}