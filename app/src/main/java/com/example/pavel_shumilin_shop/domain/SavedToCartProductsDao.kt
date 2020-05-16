package com.example.pavel_shumilin_shop.domain

import com.example.pavel_shumilin_shop.domain.model.Product

interface SavedToCartProductsDao {

    fun saveProduct(product: Product)

    fun removeProduct(product: Product)

    fun getAllProducts(): List<Product>
}