package com.example.pavel_shumilin_shop.presenter.catalog

import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.interactor.AddProductToCartUseCase
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.BasePresenter
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CatalogPresenter @Inject constructor(
    private val mainApi: MainApi,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : BasePresenter<CatalogView>() {

//    private val categories = mutableListOf(
//        "Телевизоры",
//        "Телефоны",
//        "Планшеты",
//        "Часы",
//        "Компютеры",
//        "Ноутбуки"
//    )

    private var products = mutableListOf(
        Product("1", "Name1", 123.5, 0, "description", "http:img", listOf(
            Product.Attribute("attr1", "123")
        )),
        Product("2", "Name2", 900.5, 0, "description", "http:img", listOf(
            Product.Attribute("attr2", "123")
        ))
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val category = mainApi.allCategoriesWithProducts()
            products = category[0].products.toMutableList()
            viewState.setCategoryProducts(products)
        }
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
//        val productIds = viewedProductDao.getAllProducts()
//        viewState.showProductIds(productIds)
    }

//    fun removeItem(category: String) {
//        val position = categories.indexOf(category)
//        categories.remove(category)
//        viewState.removeItem(position)
//    }

//    fun addProductToCart() {
//        launch {
//            addProductToCartUseCase()
//        }
//    }
}