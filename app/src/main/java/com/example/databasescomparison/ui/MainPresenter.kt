package com.example.databasescomparison.ui

import com.example.databasescomparison.data.Repository
import com.example.databasescomparison.data.model.remotenews.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainPresenter(private val repository: Repository) {

    private val scope by lazy { CoroutineScope(Job() + Dispatchers.IO) }

    private lateinit var model: MainModel

    fun attachView(model: MainModel) {
        this.model = model
        requestHeadliners()
    }

    fun requestHeadliners() = repository.requestHeadlines { timeAndShow(it) }

    fun requestByQuery(query: String) = repository.requestNewsByQuery(query) { timeAndShow(it) }

    private fun timeAndShow(articles: List<Article>) {
        model.showNewsList(articles)
        scope.launch {
            model.updateTimerResults(
                repository.addArticles(articles)
            )
        }
    }
}