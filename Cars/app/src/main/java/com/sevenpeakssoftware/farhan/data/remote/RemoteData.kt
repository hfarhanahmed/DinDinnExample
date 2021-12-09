package com.sevenpeakssoftware.farhan.data.remote

import com.sevenpeakssoftware.farhan.data.Resource
import com.sevenpeakssoftware.farhan.data.dto.articles.Articles
import com.sevenpeakssoftware.farhan.data.dto.articles.ArticlesItem
import com.sevenpeakssoftware.farhan.data.dto.articles.ArticlesResponse
import com.sevenpeakssoftware.farhan.data.error.NETWORK_ERROR
import com.sevenpeakssoftware.farhan.data.error.NO_INTERNET_CONNECTION
import com.sevenpeakssoftware.farhan.data.remote.service.ArticlesService
import com.sevenpeakssoftware.farhan.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by FarhanAhmed
 */

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) : RemoteDataSource {
    override suspend fun requestArticles(): Resource<Articles> {
        val recipesService = serviceGenerator.createService(ArticlesService::class.java)
        return when (val response = processCall(recipesService::fetchRecipes)) {
            is ArticlesResponse -> {
                Resource.Success(data = Articles(response.content as ArrayList<ArticlesItem>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
