package com.sevenpeakssoftware.farhan.data.remote.service

import com.sevenpeakssoftware.farhan.data.dto.articles.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by FarhanAhmed
 */

interface ArticlesService {
    @GET("article/get_articles_list")
    suspend fun fetchRecipes(): Response<ArticlesResponse>
}
