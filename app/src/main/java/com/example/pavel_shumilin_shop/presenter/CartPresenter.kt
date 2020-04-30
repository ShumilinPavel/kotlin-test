package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import com.example.pavel_shumilin_shop.domain.model.CartProduct
import com.example.pavel_shumilin_shop.domain.model.CartProductFactory
import com.example.pavel_shumilin_shop.domain.model.Product
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CartPresenter(
    private val viewedProductDao: ViewedProductDao
) : MvpPresenter<CartView>() {

    private val factory = CartProductFactory()

    private val products = mutableListOf(
        factory.createCartProduct(1, "someProd0", "123321", 1200.0, 0),
        factory.createCartProduct(2, "someProd1", "123321", 1200.0, 0),
        factory.createCartProduct(3, "someProd2", "123321", 1200.0, 0),
        factory.createCartProduct(4, "someProd3", "123321", 1200.0, 0),
        factory.createCartProduct(5, "someProd4", "123321", 1200.0, 0),
        factory.createCartProduct(6, "someProd5", "123321", 1200.0, 0)
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setProductItems(products)
    }

    fun removeProduct(product: CartProduct) {
        for (position in 0..products.size) {
            if (product == products[position]) {
                products.removeAt(position)
                viewState.removeProductItem(position)
                break
            }
        }
    }

    fun addProduct(product: CartProduct) {
        products.add(product)
        val position = products.lastIndex
        viewState.addProductItem(position)
    }

    fun onProductClick(product: CartProduct) {
        viewState.showProductDetailed(product)
    }
}