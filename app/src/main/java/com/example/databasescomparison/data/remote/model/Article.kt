package com.example.databasescomparison.data.remote.model

import com.google.gson.annotations.Expose

data class Article(
    @Expose
    val author: String,
    @Expose
    val content: String,
    @Expose
    val description: String,
    @Expose
    val publishedAt: String,
    @Expose
    val source: Source,
    @Expose
    val title: String,
    @Expose
    val url: String,
    @Expose
    val urlToImage: String
)