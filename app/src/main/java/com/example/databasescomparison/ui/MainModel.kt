package com.example.databasescomparison.ui

import com.example.databasescomparison.data.remote.model.NewsBody

interface MainModel {
    fun showNewsList(data: NewsBody)
}