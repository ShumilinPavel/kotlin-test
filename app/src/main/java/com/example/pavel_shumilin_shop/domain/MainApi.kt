package com.example.pavel_shumilin_shop.domain

import com.example.pavel_shumilin_shop.domain.model.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class Category(
    val name: String,
    val products: List<Product>
)

interface MainApi {

    @GET("products/all/default")
    suspend fun allProducts(): List<Product>

    @GET("products/allWithCategories/Shumilin/")
    suspend fun allCategoriesWithProducts(): List<Category>

    @GET("products/all/Shumilin")
    suspend fun allCartProducts(): List<Product>

    @POST("products/all/{author}/")
    suspend fun addProduct(@Body product: Product)
}