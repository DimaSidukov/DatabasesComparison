package com.example.databasescomparison.ui

import com.example.databasescomparison.data.Repository
import kotlinx.coroutines.*

class MainPresenter(private val repository: Repository) {

    val scope by lazy { CoroutineScope(Job() + Dispatchers.IO) }

    private lateinit var model: MainModel

    fun attachView(model: MainModel) {
        this.model = model
        requestHeadliners()
    }

    private fun requestHeadliners() {
        repository.requestHeadlines {
            model.showNewsList(it)
            scope.launch {
                repository.addArticles(it)
            }
        }
    }

    fun requestByQuery(query: String) {
        repository.requestNewsByQuery(query) {
            model.showNewsList(it)
            scope.launch {
                repository.addArticles(it)
            }
        }
    }
}