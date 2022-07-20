package com.example.databasescomparison.data.model.remotenews

import com.google.gson.annotations.Expose

data class Article(
    @Expose
    var author: String,
    @Expose
    var content: String,
    @Expose
    var description: String,
    @Expose
    var publishedAt: String,
    @Expose
    var source: Source,
    @Expose
    var title: String,
    @Expose
    var url: String,
    @Expose
    var urlToImage: String
)