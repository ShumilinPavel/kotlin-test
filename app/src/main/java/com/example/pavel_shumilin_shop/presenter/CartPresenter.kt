package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import com.example.pavel_shumilin_shop.domain.model.CartProduct
import com.example.pavel_shumilin_shop.domain.model.CartProductFactory
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.UnknownHostException

@InjectViewState
class CartPresenter(
    private val mainApi: MainApi,
    private val viewedProductDao: ViewedProductDao
) : BasePresenter<CartView>() {

    private val factory = CartProductFactory()

    private var products = mutableListOf<CartProduct>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val remoteProducts = mainApi.allProducts()
            products = remoteProducts.map {
                factory.createCartProduct(
                    it.id.toInt(),
                    it.name,
                    it.imageUrl,
                    it.price,
                    it.discountPercent
                )
            }.toMutableList()
            viewState.setProductItems(products)
        }
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is UnknownHostException) {
            viewState.showToastMessage("Не удалось загрузить продукты")
        }
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