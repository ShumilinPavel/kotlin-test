package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import com.example.pavel_shumilin_shop.domain.model.CartProduct
import moxy.MvpPresenter

class DetailedPresenter(private val viewedProductDao: ViewedProductDao) :
    MvpPresenter<DetailedView>() {
    fun onProductShow(product: CartProduct) {
        viewedProductDao.addProduct(product.id.toLong())
    }
}