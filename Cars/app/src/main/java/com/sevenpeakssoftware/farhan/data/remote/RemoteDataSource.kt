package com.sevenpeakssoftware.farhan.data.remote

import com.sevenpeakssoftware.farhan.data.Resource
import com.sevenpeakssoftware.farhan.data.dto.articles.Articles

/**
 * Created by FarhanAhmed
 */

internal interface RemoteDataSource {
    suspend fun requestArticles(): Resource<Articles>
}
