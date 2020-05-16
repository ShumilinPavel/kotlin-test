package com.example.pavel_shumilin_shop.presenter.detailed

import com.example.pavel_shumilin_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailedView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAttributeItems(attributes: List<Product.Attribute>)
}