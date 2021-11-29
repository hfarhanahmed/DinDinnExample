package com.sevenpeakssoftware.farhan.data

import com.sevenpeakssoftware.farhan.data.dto.recipes.Recipes
import com.sevenpeakssoftware.farhan.data.dto.login.LoginRequest
import com.sevenpeakssoftware.farhan.data.dto.login.LoginResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by FarhanAhmed
 */

interface DataRepositorySource {
    suspend fun requestRecipes(): Flow<Resource<Recipes>>
    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun addToFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun removeFromFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun isFavourite(id: String): Flow<Resource<Boolean>>
}
