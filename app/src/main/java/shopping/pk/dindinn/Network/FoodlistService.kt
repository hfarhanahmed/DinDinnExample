package shopping.pk.dindinn.Network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import shopping.pk.dindinn.Models.ExampleModel
import shopping.pk.dindinn.Models.FoodItem
import shopping.pk.dindinn.Network.dtos.FoodlistResponse

interface FoodlistService {
    @Headers("Accept: application/json")
    @GET("search")
    suspend fun search(
        @Query("categoryId") caategoryId: String? = null,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 20
    ): FoodlistResponse

    @Headers("Accept: application/json")
    @GET("j/{id}")
    suspend fun fetch(@Path("id") id: String): FoodItem

    @Headers("Accept: application/json")
    @GET("")
    suspend fun random(): FoodItem


    @Headers("Accept: application/json")
    @GET("todos")
    suspend fun exampleList(): List<ExampleModel>

}