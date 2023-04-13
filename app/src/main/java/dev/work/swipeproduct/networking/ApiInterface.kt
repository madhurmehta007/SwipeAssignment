package dev.work.swipeproduct.networking

import dev.work.swipeproduct.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("get")
    suspend fun getProducts(): Response<List<ProductDataItem>>


    @Multipart
    @POST("add")
    suspend fun pushPost2(
        @Part("price") price:Double,
        @Part("product_name") product_name:RequestBody,
        @Part("product_type") product_type:RequestBody,
        @Part("tax") tax:Double,
        @Part image: MultipartBody.Part
    ):Response<PostResponse>

    @Multipart
    @POST("add")
    suspend fun postWithoutImage(
        @Part("price") price:Double,
        @Part("product_name") product_name:RequestBody,
        @Part("product_type") product_type:RequestBody,
        @Part("tax") tax:Double,
    ):Response<PostResponse>

}