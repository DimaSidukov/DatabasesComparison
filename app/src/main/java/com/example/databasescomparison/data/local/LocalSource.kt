package com.example.databasescomparison.data.local

import com.example.databasescomparison.data.local.source.sqloh.SQLOHDatabaseHelper
import com.example.databasescomparison.data.model.remotenews.Article

class LocalSource(private val sqloh: SQLOHDatabaseHelper) {

    fun addArticle(article: Article) {
        // why does it work in main thread? how to move execution to another thread?
        // create 4 coroutines and for each time the invocation of methods. return the results in seconds
        sqloh.addArticle(article)
    }

    fun addArticles(articles: List<Article>) {
        sqloh.addArticles(articles)
    }

    fun getArticles() : List<Article> {
        return sqloh.articles
    }

    fun deleteArticle(article: Article) {
        sqloh.deleteArticle(article)
    }

    fun deleteAllArticles() {
        sqloh.deleteAllArticles()
    }

    fun updateArticle(article: Article) {
        sqloh.updateArticle(article)
    }

}