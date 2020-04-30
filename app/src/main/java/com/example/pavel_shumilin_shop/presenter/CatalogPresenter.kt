package com.example.pavel_shumilin_shop.presenter

import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatalogPresenter(
    private val viewedProductDao: ViewedProductDao
) : MvpPresenter<CatalogView>() {

    private val list = mutableListOf(
        "Телевизоры",
        "Телефоны",
        "Планшеты",
        "Часы",
        "Компютеры",
        "Ноутбуки"
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        val productIds = viewedProductDao.getAllProducts()
        viewState.showProductIds(productIds)
    }

    fun setData() {
        viewState.setCategories(list)
    }

    fun removeItem(category: String) {
        val position = list.indexOf(category)
        list.remove(category)
        viewState.removeItem(position)
    }

}