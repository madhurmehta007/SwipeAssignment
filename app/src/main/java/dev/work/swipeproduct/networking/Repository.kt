package dev.work.swipeproduct.networking

import dev.work.swipeproduct.models.PostResponse
import dev.work.swipeproduct.models.ProductDataItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getLatestProducts(): Response<List<ProductDataItem>> {
        return apiInterface.getProducts()
    }

    suspend fun pushPost2(
        price: Double,
        product_name: RequestBody,
        product_type: RequestBody,
        tax: Double,
        image: MultipartBody.Part
    ): Response<PostResponse> {
        return apiInterface.pushPost2(price, product_name, product_type, tax, image)
    }

    suspend fun postWithoutImage(
        price: Double,
        product_name: RequestBody,
        product_type: RequestBody,
        tax: Double
    ): Response<PostResponse> {
        return apiInterface.postWithoutImage(price, product_name, product_type, tax)
    }


}