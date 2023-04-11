package dev.work.swipeproduct.networking

import dev.work.swipeproduct.models.ProductData
import dev.work.swipeproduct.models.ProductDataItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("get")
    suspend fun getProducts(): Response<List<ProductDataItem>>
}