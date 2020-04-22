package com.example.pavel_shumilin_shop.ui

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleTagStrategy::class)
interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleTagStrategy::class)
    fun setCategories(list: List<String>)

    @StateStrategyType(AddToEndSingleTagStrategy::class)
    fun removeItem(position: Int)
}
