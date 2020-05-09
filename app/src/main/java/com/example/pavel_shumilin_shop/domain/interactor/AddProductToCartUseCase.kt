package com.example.pavel_shumilin_shop.domain.interactor

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val mainApi: MainApi,
    private val viewedProductDao: ViewedProductDao
) {

    suspend operator fun invoke() {
        // TODO realization
    }
}