package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.Product
import com.example.pavel_shumilin_shop.ui.CartView
import moxy.MvpPresenter

class CartPresenter : MvpPresenter<CartView>() {

    var products = mutableListOf(
        Product(100.0, 1, "Клавиатура"),
        Product(200.0, 2, "Монитор"),
        Product(300.0, 3, "Мышь"),
        Product(400.0, 4, "Наушники"),
        Product(500.0, 5, "Лампа"),
        Product(600.0, 6, "Будильник"),
        Product(700.0, 7, "Кабель"),
        Product(800.0, 8, "Подставка"),
        Product(900.0, 9, "Ноутбук"),
        Product(1000.0, 10, "Видеокарта"),
        Product(1100.0, 11, "Процессор")
    )

    fun setProducts() {
        viewState.setProductItems(products)
    }

    fun removeProduct(product: Product) {
        for (position in 0..products.size) {
            if (product == products[position]) {
                products.removeAt(position)
                viewState.removeProductItem(position)
                break
            }
        }
    }

    fun addProduct(product: Product) {
        products.add(product)
        val position = products.lastIndex
        viewState.addProductItem(position)
    }

}