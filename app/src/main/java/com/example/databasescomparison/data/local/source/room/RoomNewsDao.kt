package com.example.databasescomparison.data.local.source.room

import androidx.room.*
import com.example.databasescomparison.data.model.roomnews.RoomArticle

@Dao
interface RoomNewsDao {

    @Query("SELECT * FROM RoomArticle")
    fun getRoomArticles(): List<RoomArticle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomArticle(article: RoomArticle)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomArticles(articles: List<RoomArticle>)

    @Update
    suspend fun updateRoomArticle(article: RoomArticle)

    @Delete
    suspend fun deleteRoomArticle(article: RoomArticle)

    @Query("DELETE FROM RoomArticle")
    suspend fun deleteAllRoomArticles()

}