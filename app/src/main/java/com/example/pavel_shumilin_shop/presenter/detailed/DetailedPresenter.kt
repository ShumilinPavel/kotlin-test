package com.example.pavel_shumilin_shop.presenter.detailed

import com.example.pavel_shumilin_shop.domain.SavedToCartProductsDao
import com.example.pavel_shumilin_shop.domain.interactor.AddProductToCartUseCase
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.BasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedPresenter @Inject constructor(
    private val savedToCartProductsDao: SavedToCartProductsDao,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : BasePresenter<DetailedView>() {

    private var attributes = emptyList<Product.Attribute>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setAttributeItems(attributes)
    }

    fun onProductShow(product: Product) {
        attributes = product.attributes
    }

    fun onAddToCartButtonClick(product: Product) {
        savedToCartProductsDao.saveProduct(product)
    }

    fun addProductToCartSendOnServer(product: Product) {
        launch {
            addProductToCartUseCase(product)
        }
    }
}