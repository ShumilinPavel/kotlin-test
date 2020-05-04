package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatalogPresenter(
    private val viewedProductDao: ViewedProductDao
) : MvpPresenter<CatalogView>() {

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

}