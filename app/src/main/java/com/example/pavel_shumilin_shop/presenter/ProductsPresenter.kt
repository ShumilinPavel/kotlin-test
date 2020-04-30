package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.domain.model.CreateOrderModel
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProductsPresenter : MvpPresenter<ProductsView>() {

    private val products: List<Product> = listOf(
        Product(
            price = 123.5,
            salePercent = 30,
            productName = "IPhone Case"
        ),
        Product(
            price = 124.5,
            salePercent = 15,
            productName = "Samsung Case"
        )
    )
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

    fun checkMiddleName(text: String) {
        val isValid = validateTextLength(text)
        if (isValid) model.middleName = text
        viewState.showErrorForMiddleName(!isValid)
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




    fun printProductsInfo() {
        products.forEach {
            viewState.print("${it.getProductName()}: ${it.calcDiscountPrice()}")
        }
        printTotalPrice()
    }

    /**
     * print total price of products from the shopping cart with the applied discount
     */
    fun printTotalPrice() {
        viewState.print(products.sumByDouble { it.calcDiscountPrice() })
    }

    fun printProductNames() {
        products.forEach {
            viewState.print(it.getProductName())
        }
    }

    fun getProducts(): List<Product> = products
}