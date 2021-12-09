package com.sevenpeakssoftware.farhan.data.localDb

import com.sevenpeakssoftware.farhan.data.Resource
import com.sevenpeakssoftware.farhan.data.dto.articles.Article
import javax.inject.Inject

class LocalData @Inject
constructor(private val appDatabase: AppDatabase): LocalDataSource {
    override suspend fun requestLocalArticles(): Resource<List<Article>> {
        val response = appDatabase.articleDao().getAll()
        return if(response.isNotEmpty()){
            Resource.LocalSuccess(data = response)
        }else{
            Resource.LocalDataError(errorCode = 0)
        }
    }

    override suspend fun insertLocalArticles(articles: List<Article>): Resource<List<Long>>{
        val response = appDatabase.articleDao().insertAll(articles)
        return Resource.LocalInsertSuccess(data = response)
    }
}