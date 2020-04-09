package com.example.pavel_shumilin_shop

class ShoppingCartPresenter(
    private val view: ProductsView,
    private val products: List<Product> = emptyList()
) {
    fun printProductsInfo() {
        products.forEach {
            view.print("${it.getName()}: ${it.calcDiscountPrice()}")
        }
        printTotalPrice()
    }

    /**
     * print total price of products from the shopping cart with the applied discount
     */
    fun printTotalPrice() {
        view.print(products.sumByDouble { it.calcDiscountPrice() })
    }

    fun printProductNames() {
        products.forEach {
            view.print(it.getName())
        }
    }

    fun getProducts(): List<Product> = products
}