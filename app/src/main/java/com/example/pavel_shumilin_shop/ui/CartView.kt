package com.example.pavel_shumilin_shop.ui

import com.example.pavel_shumilin_shop.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductItems(products: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeProductItem(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addProductItem(position: Int)
}