package com.example.pavel_shumilin_shop.presenter.cart

import com.example.pavel_shumilin_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductItems(products: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeProductItem(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addProductItem(position: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProductDetailed(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showToastMessage(message: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun goToCheckoutActivity(productsInCart: List<Product>)
}