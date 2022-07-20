package com.example.databasescomparison.data

import com.example.databasescomparison.data.local.LocalSource
import com.example.databasescomparison.data.model.remotenews.Article
import com.example.databasescomparison.data.remote.RemoteSource

class Repository(private val localSource: LocalSource, private val remoteSource: RemoteSource) {

    fun requestHeadlines(onSuccess: (List<Article>) -> Unit) =
        remoteSource.requestHeadlines(onSuccess)

    fun requestHeadlinesOnPage(page: Int, onSuccess: (List<Article>) -> Unit) =
        remoteSource.requestHeadlinesOnPage(page, onSuccess)

    fun requestNewsByQuery(query: String, onSuccess: (List<Article>) -> Unit) =
        remoteSource.requestNewsByQuery(query, onSuccess)

    fun requestNewsByQueryOnPage(query: String, page: Int, onSuccess: (List<Article>) -> Unit) =
        remoteSource.requestNewsByQueryOnPage(query, page, onSuccess)

    fun addArticle(article: Article) = localSource.addArticle(article)

    fun addArticles(articles: List<Article>) = localSource.addArticles(articles)

    fun updateArticle(article: Article) = localSource.updateArticle(article)

    fun getArticles() = localSource.getArticles()

    fun deleteArticle(article: Article) = localSource.deleteArticle(article)

    fun deleteAllArticles() = localSource.deleteAllArticles()

}