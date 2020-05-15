package com.example.pavel_shumilin_shop.presenter.detailed

import android.util.Log
import com.example.pavel_shumilin_shop.domain.ViewedProductDao
import com.example.pavel_shumilin_shop.domain.interactor.AddProductToCartUseCase
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.BasePresenter
import javax.inject.Inject

class DetailedPresenter @Inject constructor(
    private val viewedProductDao: ViewedProductDao,
    private val addProductToCartUseCase: AddProductToCartUseCase
//    private val product: Product
) : BasePresenter<DetailedView>() {

    private var attributes = listOf(
        Product.Attribute("Производитель", "Рога и копыта"),
        Product.Attribute("Производитель", "Рога и копыта"),
        Product.Attribute("Производитель", "Рога и копыта"),
        Product.Attribute("Производитель", "Рога и копыта"),
        Product.Attribute("Производитель", "Рога и копыта"),
        Product.Attribute("Производитель", "Рога и копыта")
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setAttributeItems(attributes)
    }

    fun onProductShow(product: Product) {
        viewedProductDao.addProduct(product.id.toLong())
        attributes = product.attributes
    }

    fun onAddToCartButtonClick(product: Product) {
        // Отправка на сервер
//        launch {
//            addProductToCartUseCase(product)
//        }
        Log.d("MY_TAG", product.toString())
    }


//    class DetailedPresenterFactory @Inject constructor(
//        private val viewedProductDao: ViewedProductDao,
//        private val addProductToCartUseCase: AddProductToCartUseCase
//    ) {
//        fun create(product: Product): DetailedPresenter {
//            return DetailedPresenter(
//                viewedProductDao,
//                addProductToCartUseCase,
//                product
//            )
//        }
//    }
}