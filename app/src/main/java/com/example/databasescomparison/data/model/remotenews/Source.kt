package com.example.databasescomparison.data.model.remotenews

import com.google.gson.annotations.Expose

data class Source(
    @Expose
    val id: String,
    @Expose
    val name: String
)