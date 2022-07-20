package com.example.databasescomparison.data.local.source.room

import com.example.databasescomparison.data.model.remotenews.Article
import com.example.databasescomparison.data.model.roomnews.toRoomArticle

class RoomDaoHelper(private val roomNewsDao: RoomNewsDao) {

    fun getRoomArticles() = roomNewsDao.getRoomArticles()

    suspend fun insertRoomArticle(article: Article) =
        roomNewsDao.insertRoomArticle(article.toRoomArticle())

    suspend fun insertRoomArticles(articles: List<Article>) =
        roomNewsDao.insertRoomArticles(articles.map { it.toRoomArticle() })

    suspend fun updateRoomArticle(article: Article) =
        roomNewsDao.updateRoomArticle(article.toRoomArticle())

    suspend fun deleteRoomArticle(article: Article) =
        roomNewsDao.deleteRoomArticle(article.toRoomArticle())

    suspend fun deleteAllRoomArticles() = roomNewsDao.deleteAllRoomArticles()

}