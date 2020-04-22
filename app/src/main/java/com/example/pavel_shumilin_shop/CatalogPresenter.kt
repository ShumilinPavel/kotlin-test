package com.example.pavel_shumilin_shop

import com.example.pavel_shumilin_shop.ui.CatalogView
import moxy.MvpPresenter

class CatalogPresenter : MvpPresenter<CatalogView>() {
    private val list = mutableListOf("Телевизоры", "Телефоны", "Планшеты", "Часы", "Компьютеры", "Ноутбуки")

    fun setData() {
        viewState.setCategories(list)
    }

    fun removeItem(category: String) {
        val position = list.indexOf(category)
        list.remove(category)
        viewState.removeItem(position)
    }
}