package com.example.pavel_shumilin_shop.presenter.checkout

import com.example.pavel_shumilin_shop.domain.model.CreateOrderModel
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CheckoutPresenter : MvpPresenter<CheckoutView>() {

    private val model = CreateOrderModel()

    fun checkSurname(text: String) {
        val isValid = validateTextLength(text)
        if (isValid) model.surname = text
        viewState.showErrorForSurname(!isValid)
    }

    fun checkName(text: String) {
        val isValid = validateTextLength(text)
        if (isValid) model.name = text
        viewState.showErrorForName(!isValid)
    }

    private fun validateTextLength(text: String): Boolean = text.length >= 3

    fun checkPhoneNumber(phoneNumber: String) {
        val isValid = validatePhoneNumber((phoneNumber))
        if (isValid) model.phoneNumber = phoneNumber
        viewState.showErrorForPhone(!isValid)
    }

    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        val isNumeric = phoneNumber.toLongOrNull() != null
        val isFirstValidFormat = phoneNumber.length == 11 && phoneNumber.first() == '8'
        val isSecondValidFormat = phoneNumber.length == 12 && phoneNumber.startsWith("+7")
        return (isNumeric) && (isFirstValidFormat || isSecondValidFormat)
    }
}