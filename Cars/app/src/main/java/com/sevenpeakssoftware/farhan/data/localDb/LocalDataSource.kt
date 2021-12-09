package com.sevenpeakssoftware.farhan.data.localDb

import com.sevenpeakssoftware.farhan.data.Resource
import com.sevenpeakssoftware.farhan.data.dto.articles.Article

/**
 * Created by FarhanAhmed
 */

interface LocalDataSource {
    suspend fun requestLocalArticles(): Resource<List<Article>>
    suspend fun insertLocalArticles(articles: List<Article>): Resource<List<Long>>
}
