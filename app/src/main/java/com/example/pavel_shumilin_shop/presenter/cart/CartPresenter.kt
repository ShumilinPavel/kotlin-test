package com.example.pavel_shumilin_shop.presenter.cart

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.UnknownHostException
import javax.inject.Inject

@InjectViewState
class CartPresenter @Inject constructor(
    private val mainApi: MainApi
) : BasePresenter<CartView>() {

    private var productsInCart = mutableListOf<Product>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            productsInCart = mainApi.allProducts().toMutableList()
//            productsInCart = mainApi.allProductsInCart().toMutableList()
            viewState.setProductItems(productsInCart)
        }
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        if (e is UnknownHostException) {
            viewState.showToastMessage("Не удалось загрузить продукты")
        }
    }

    fun removeProduct(product: Product) {
        for (position in 0..productsInCart.size) {
            if (product == productsInCart[position]) {
                productsInCart.removeAt(position)
                viewState.removeProductItem(position)
                break
            }
        }
    }

    fun onProductClick(product: Product) {
        viewState.showProductDetailed(product)
    }

    fun onCartCheckoutBtnClick() {
        viewState.goToCheckoutActivity(productsInCart)
    }
}