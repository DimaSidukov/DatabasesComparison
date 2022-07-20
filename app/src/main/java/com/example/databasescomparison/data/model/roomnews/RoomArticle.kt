package com.example.databasescomparison.data.model.roomnews

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.databasescomparison.data.model.remotenews.Article

@Entity
data class RoomArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,
    @ColumnInfo(name = "sourceId")
    val sourceId: String?,
    @ColumnInfo(name = "sourceName")
    val sourceName: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?
)

fun Article.toRoomArticle() = RoomArticle(
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt,
    sourceId = this.source.id,
    sourceName = this.source.name,
    title = this.title,
    url = this.url,
    urlToImage = this.urlToImage
)
