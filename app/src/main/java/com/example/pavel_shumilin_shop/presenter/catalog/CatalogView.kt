package com.example.pavel_shumilin_shop.presenter.catalog

import com.example.pavel_shumilin_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategoryProducts(list: List<Product>)
}