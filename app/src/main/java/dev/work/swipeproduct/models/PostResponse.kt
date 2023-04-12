package dev.work.swipeproduct.models

data class PostResponse(
    val message: String,
    val product_details: ProductDetails,
    val product_id: Int,
    val success: Boolean
)