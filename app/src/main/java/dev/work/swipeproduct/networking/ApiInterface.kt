package dev.work.swipeproduct.networking

import dev.work.swipeproduct.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("get")
    suspend fun getProducts(): Response<List<ProductDataItem>>

    @FormUrlEncoded
    @POST("add")
    suspend fun pushPost2(
        @Field("price") price:Double,
        @Field("product_name") product_name:String,
        @Field("product_type") product_type:String,
        @Field("tax") tax:Double,
    ):Response<PostResponse>
}