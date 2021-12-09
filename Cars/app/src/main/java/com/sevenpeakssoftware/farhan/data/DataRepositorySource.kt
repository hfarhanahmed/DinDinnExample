package com.sevenpeakssoftware.farhan.data

import com.sevenpeakssoftware.farhan.data.dto.articles.Article
import com.sevenpeakssoftware.farhan.data.dto.articles.Articles
import kotlinx.coroutines.flow.Flow

/**
 * Created by FarhanAhmed
 */

interface DataRepositorySource {
    suspend fun requestArticles(): Flow<Resource<Articles>>
    suspend fun requestLocalArticles(): Flow<Resource<List<Article>>>
    suspend fun insertLocalArticles(articles: List<Article>): Flow<Resource<List<Long>>>
}
