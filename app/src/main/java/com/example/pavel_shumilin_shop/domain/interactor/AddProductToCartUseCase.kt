package com.example.pavel_shumilin_shop.domain.interactor

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import com.example.pavel_shumilin_shop.domain.model.Product
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val mainApi: MainApi,
    private val viewedProductDao: ViewedProductDao
) {

    suspend operator fun invoke(product: Product) {
       return mainApi.addProduct(product)
    }
}