package com.example.databasescomparison.data.remote.model

import com.google.gson.annotations.Expose

data class Source(
    @Expose
    val id: String,
    @Expose
    val name: String
)