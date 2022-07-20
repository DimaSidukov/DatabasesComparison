package com.example.databasescomparison.data.local

import com.example.databasescomparison.data.local.source.room.RoomDaoHelper
import com.example.databasescomparison.data.local.source.sqloh.SQLOHDatabaseHelper
import com.example.databasescomparison.data.model.remotenews.Article
import com.example.databasescomparison.data.model.timer.DbTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

class LocalSource(private val sqloh: SQLOHDatabaseHelper, private val roomDao: RoomDaoHelper) {

    private val scope by lazy { CoroutineScope(Job() + Dispatchers.IO) }

    fun addArticle(article: Article) {
        sqloh.addArticle(article)
    }

    fun addArticles(articles: List<Article>): DbTimer {
        // use coroutines instead of runBlocking
        val dbTimer = DbTimer()
        runBlocking(Dispatchers.IO) {
            dbTimer.sqlohTime = timeMethod { sqloh.addArticles(articles) }
            dbTimer.roomTime = timeMethod { roomDao.insertRoomArticles(articles) }
        }
        return dbTimer
    }

    fun getArticles(): List<Article> {
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

    private suspend fun timeMethod(func: suspend () -> Unit): Long {
        val begin = System.currentTimeMillis()
        func()
        val end = System.currentTimeMillis()

        return end - begin
    }

}