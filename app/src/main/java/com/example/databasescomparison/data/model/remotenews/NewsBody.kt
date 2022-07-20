package com.example.databasescomparison.data.model.remotenews

import com.google.gson.annotations.Expose

data class NewsBody(
    @Expose
    val articles: List<Article>,
    @Expose
    val status: String,
    @Expose
    val totalResults: Int
)