package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.model.CartProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductItems(products: List<CartProduct>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeProductItem(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addProductItem(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProductDetailed(product: CartProduct)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showToastMessage(message: String)
}