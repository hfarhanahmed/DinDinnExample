package com.sevenpeakssoftware.farhan.data.localDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sevenpeakssoftware.farhan.data.dto.articles.Article
import com.sevenpeakssoftware.farhan.data.localDb.dao.ArticleDao

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        lateinit var database: AppDatabase;
        fun getInstance(context: Context): AppDatabase {
            if(!this::database.isInitialized){
                database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "articles-database"
                ).build()
            }
            return database;
        }
    }

}