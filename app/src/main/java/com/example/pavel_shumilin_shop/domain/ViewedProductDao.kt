package com.example.pavel_shumilin_shop.domain

interface ViewedProductDao {
    /**
     * save this product id as viewed
     */
    fun addProduct(productId: Long)

    /**
     * get all viewed product ids
     * might be empty
     */
    fun getAllProducts(): List<Long>
}