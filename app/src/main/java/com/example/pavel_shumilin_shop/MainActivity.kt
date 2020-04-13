package com.example.pavel_shumilin_shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.truncate

class MainActivity : AppCompatActivity(), ProductsView {

    private val presenter = ShoppingCartPresenter(
        this, listOf(
            Product(price = 123.5, salePercent = 30, name = "IPhone Case"),
            Product(price = 124.5, salePercent = 15, name = "Samsung Case")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // presenter.printProductsInfo()

        val products = listOf(
            Product(price = 123.5, salePercent = 30, name = "IPhone Case"),
            Product(price = 124.5, salePercent = 15, name = "Samsung Case")
        )

        var initialPrice = 0.0
        var discountPrice = 0.0
        products.forEach {
            initialPrice += it.getPrice()
            discountPrice += it.calcDiscountPrice()
        }

//        checkoutProductsPrice.text = initialPrice.toString()
//        checkoutFinalPrice.text = discountPrice.toString()
//        checkoutSaleAmount.text = (initialPrice - discountPrice).toString()
    }

    override fun print(price: Double) {
        // Log.d("Print", "$price")
        checkoutFinalPrice.text = price.toString()
    }

    override fun print(message: String) {
        Log.d("Print", message)
    }
}
