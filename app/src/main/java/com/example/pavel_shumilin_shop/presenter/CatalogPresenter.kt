package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import com.example.pavel_shumilin_shop.domain.interactor.AddProductToCartUseCase
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CatalogPresenter @Inject constructor(
    private val viewedProductDao: ViewedProductDao,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : BasePresenter<CatalogView>() {

    private val categories = mutableListOf(
        "Телевизоры",
        "Телефоны",
        "Планшеты",
        "Часы",
        "Компютеры",
        "Ноутбуки"
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setCategories(categories)
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        val productIds = viewedProductDao.getAllProducts()
        viewState.showProductIds(productIds)
    }

    fun removeItem(category: String) {
        val position = categories.indexOf(category)
        categories.remove(category)
        viewState.removeItem(position)
    }

    fun addProductToCart() {
        launch {
            addProductToCartUseCase()
        }
    }
}