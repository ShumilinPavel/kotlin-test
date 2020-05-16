package com.example.pavel_shumilin_shop.presenter.cart

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.SavedToCartProductsDao
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.UnknownHostException
import javax.inject.Inject

@InjectViewState
class CartPresenter @Inject constructor(
    private val savedToCartProductsDao: SavedToCartProductsDao,
    private val mainApi: MainApi
) : BasePresenter<CartView>() {

    private var cartProducts = mutableListOf<Product>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setCartProductsFromDao()
    }

    private fun setCartProductsFromDao() {
        cartProducts = savedToCartProductsDao.getAllProducts().toMutableList()
        if (cartProducts.size != 0) {
            viewState.setProductItems(cartProducts)
        }
        else {
            viewState.showNoCartProductsTextView()
        }
    }

    fun setCartProductsFromServer() {
        launch {
            cartProducts = mainApi.allCartProducts().toMutableList()
            viewState.setProductItems(cartProducts)
        }
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is UnknownHostException) {
            viewState.showToastMessage("Не удалось загрузить продукты")
        }
    }

    fun onRemoveProduct(product: Product) {
        for (position in 0..cartProducts.size) {
            if (product == cartProducts[position]) {
                cartProducts.removeAt(position)
                viewState.removeProductItem(position)
                break
            }
        }
        savedToCartProductsDao.removeProduct(product)
        if (cartProducts.size == 0) {
            viewState.showNoCartProductsTextView()
        }
    }

    fun onProductClick(product: Product) {
        viewState.showProductDetailed(product)
    }

    fun onCartCheckoutBtnClick() {
        viewState.showCartCheckout(cartProducts)
    }
}