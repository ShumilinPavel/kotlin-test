package com.example.pavel_shumilin_shop.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CheckoutView : MvpView {
    /**
     * Outputs price in <PRICE>₽ format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
//    @StateStrategyType(AddToEndSingleStrategy::class)
//    fun print(price: Double)
//
//    @StateStrategyType(AddToEndSingleStrategy::class)
//    fun print(message: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForSurname(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForMiddleName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPhone(visible: Boolean)
}