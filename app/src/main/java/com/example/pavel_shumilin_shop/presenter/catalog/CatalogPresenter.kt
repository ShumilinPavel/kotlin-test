package com.example.pavel_shumilin_shop.presenter.catalog

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CatalogPresenter @Inject constructor(
    private val mainApi: MainApi
) : BasePresenter<CatalogView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setCatalogProductsFromServer()
    }

    private fun setCatalogProductsFromServer() {
        launch {
            val category = mainApi.allCategoriesWithProducts()
            val allProducts = mutableListOf<Product>()
            category.forEach {
                allProducts.addAll(it.products)
            }
            viewState.setCategoryProducts(allProducts)
        }
    }
}