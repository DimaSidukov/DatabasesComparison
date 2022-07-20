package com.example.databasescomparison.data.local.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.databasescomparison.data.model.roomnews.RoomArticle

@Database(entities = [RoomArticle::class], version = 1)
abstract class RoomNewsDatabase: RoomDatabase() {
    abstract fun roomNewsDao(): RoomNewsDao
}