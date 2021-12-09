package com.sevenpeakssoftware.farhan

import com.sevenpeakssoftware.farhan.TestUtil.dataStatus
import com.sevenpeakssoftware.farhan.TestUtil.initData
import com.sevenpeakssoftware.farhan.data.DataRepositorySource
import com.sevenpeakssoftware.farhan.data.Resource
import com.sevenpeakssoftware.farhan.data.dto.articles.Article
import com.sevenpeakssoftware.farhan.data.dto.articles.Articles
import com.sevenpeakssoftware.farhan.data.error.NETWORK_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Created by FarhanAhmed
 */

class TestDataRepository @Inject constructor() : DataRepositorySource {

    override suspend fun requestArticles(): Flow<Resource<Articles>> {
        return when (dataStatus) {
            DataStatus.Success -> {
                flow { emit(Resource.Success(initData())) }
            }
            DataStatus.Fail -> {
                flow { emit(Resource.DataError<Articles>(errorCode = NETWORK_ERROR)) }
            }
            DataStatus.EmptyResponse -> {
                flow { emit(Resource.Success(Articles(arrayListOf()))) }
            }
        }
    }

    override suspend fun requestLocalArticles(): Flow<Resource<List<Article>>> {
        return when (dataStatus) {
            DataStatus.Success -> {
                flow { emit(Resource.LocalSuccess(initData())) }
            }
            DataStatus.Fail -> {
                flow { emit(Resource.LocalDataError<List<Article>>(errorCode = NETWORK_ERROR)) }
            }
            DataStatus.EmptyResponse -> {
                flow { emit(Resource.LocalSuccess(listOf<Article>())) }
            }
        }
    }

    override suspend fun insertLocalArticles(articles: List<Article>): Flow<Resource<List<Long>>> {
        return when (dataStatus) {
            DataStatus.Success -> {
                flow { emit(Resource.LocalSuccess(initData())) }
            }
            DataStatus.Fail -> {
                flow { emit(Resource.LocalDataError<List<Long>>(errorCode = NETWORK_ERROR)) }
            }
            DataStatus.EmptyResponse -> {
                flow { emit(Resource.LocalSuccess(listOf<Long>())) }
            }
        }
    }
}
