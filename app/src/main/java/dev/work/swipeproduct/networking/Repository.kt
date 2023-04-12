package dev.work.swipeproduct.networking

import dev.work.swipeproduct.models.PostResponse
import dev.work.swipeproduct.models.ProductDataItem
import retrofit2.Response


class Repository {

    suspend fun getLatestProducts():Response<List<ProductDataItem>>{
        return RetrofitInstance.api.getProducts()
    }

    suspend fun pushPost2(price:Double,product_name:String,product_type:String,tax:Double):Response<PostResponse>{
        return RetrofitInstance.api.pushPost2(price,product_name,product_type,tax)
    }
}