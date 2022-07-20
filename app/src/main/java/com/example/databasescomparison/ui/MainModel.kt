package com.example.databasescomparison.ui

import com.example.databasescomparison.data.model.remotenews.Article

interface MainModel {
    fun showNewsList(data: List<Article>)
}