package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import com.example.pavel_shumilin_shop.domain.model.CartProduct
import com.example.pavel_shumilin_shop.domain.model.CartProductFactory
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CartPresenter(
    private val viewedProductDao: ViewedProductDao
) : MvpPresenter<CartView>() {

    private val factory = CartProductFactory()

    private val products = mutableListOf(
        factory.createCartProduct(0, "someProd0", "123321", 1200.0, 0),
        factory.createCartProduct(1, "someProd1", "123321", 1200.0, 0),
        factory.createCartProduct(2, "someProd2", "123321", 1200.0, 0),
        factory.createCartProduct(3, "someProd3", "123321", 1200.0, 0),
        factory.createCartProduct(4, "someProd4", "123321", 1200.0, 0),
        factory.createCartProduct(5, "someProd5", "123321", 1200.0, 0),
        factory.createCartProduct(6, "someProd6", "123321", 1200.0, 0),
        factory.createCartProduct(7, "someProd7", "123321", 1200.0, 0),
        factory.createCartProduct(8, "someProd8", "123321", 1200.0, 0),
        factory.createCartProduct(9, "someProd9", "123321", 1200.0, 0),
        factory.createCartProduct(10, "someProd10", "123321", 1200.0, 0),
        factory.createCartProduct(11, "someProd11", "123321", 1200.0, 0),
        factory.createCartProduct(12, "someProd12", "123321", 1200.0, 0),
        factory.createCartProduct(13, "someProd13", "123321", 1200.0, 0),
        factory.createCartProduct(14, "someProd14", "123321", 1200.0, 0)
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