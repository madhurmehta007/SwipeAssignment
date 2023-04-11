package dev.work.swipeproduct.networking

import dev.work.swipeproduct.models.ProductData
import dev.work.swipeproduct.models.ProductDataItem
import retrofit2.Response


class Repository {

    suspend fun getLatestProducts():Response<List<ProductDataItem>>{
        return RetrofitInstance.api.getProducts()
    }
}