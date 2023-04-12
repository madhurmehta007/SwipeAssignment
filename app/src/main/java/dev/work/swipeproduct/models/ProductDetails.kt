package dev.work.swipeproduct.models

import com.google.gson.annotations.SerializedName

data class ProductDetails(
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("product_name")
    val product_name: String?,
    @SerializedName("product_type")
    val product_type: String?,
    @SerializedName("tax")
    val tax: Double?
)