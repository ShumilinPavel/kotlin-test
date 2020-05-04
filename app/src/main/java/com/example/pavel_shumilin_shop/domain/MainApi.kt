package com.example.pavel_shumilin_shop.domain

import retrofit2.http.GET

data class RemoteProduct(
    val id: String,
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>
) {
    data class Attribute(
        val name: String,
        val value: String
    )
}

interface MainApi {

    @GET("products/all/default")
    suspend fun allProducts(): List<RemoteProduct>

}