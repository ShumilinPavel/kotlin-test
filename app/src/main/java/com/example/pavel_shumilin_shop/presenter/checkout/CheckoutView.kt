package com.example.pavel_shumilin_shop.presenter.checkout

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CheckoutView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForSurname(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPhone(visible: Boolean)
}