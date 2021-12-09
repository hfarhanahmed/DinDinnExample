package com.sevenpeakssoftware.farhan.data.localDb.dao

import androidx.room.*
import com.sevenpeakssoftware.farhan.data.dto.articles.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Query("SELECT * FROM article WHERE id IN (:articleIds)")
    fun loadAllByIds(articleIds: IntArray): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>): List<Long>

    @Delete
    fun delete(article: Article)
}