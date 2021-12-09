package com.sevenpeakssoftware.farhan.data


import com.sevenpeakssoftware.farhan.data.dto.articles.Article
import com.sevenpeakssoftware.farhan.data.dto.articles.Articles
import com.sevenpeakssoftware.farhan.data.localDb.LocalData
import com.sevenpeakssoftware.farhan.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by FarhanAhmed
 */

class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val localRepository: LocalData, private val ioDispatcher: CoroutineContext) : DataRepositorySource {

    override suspend fun requestArticles(): Flow<Resource<Articles>> {
        return flow {
            emit(remoteRepository.requestArticles())
        }.flowOn(ioDispatcher)
    }

    override suspend fun requestLocalArticles(): Flow<Resource<List<Article>>> {
        return flow {
            emit(localRepository.requestLocalArticles())
        }.flowOn(ioDispatcher)
    }

    override suspend fun insertLocalArticles(articles: List<Article>): Flow<Resource<List<Long>>>{
        return flow {
            emit(localRepository.insertLocalArticles(articles))
        }.flowOn(ioDispatcher)
    }
}
