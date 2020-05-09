package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import com.example.pavel_shumilin_shop.domain.model.CartProduct
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedPresenter @Inject constructor(
    private val viewedProductDao: ViewedProductDao,
    private val productId: String
) : BasePresenter<DetailedView>() {

    fun onProductShow(product: CartProduct) {
        viewedProductDao.addProduct(product.id.toLong())
    }


    class DetailedPresenterFactory @Inject constructor(
        private val viewedProductDao: ViewedProductDao
    ) {
        fun create(productId: String): DetailedPresenter {
            return DetailedPresenter(
                viewedProductDao,
                productId
            )
        }
    }
}