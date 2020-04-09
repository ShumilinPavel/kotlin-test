package com.example.pavel_shumilin_shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.util.*
import kotlin.math.truncate

class MainActivity : AppCompatActivity(), ProductsView {

    private val presenter = ShoppingCartPresenter(this, listOf(
        Product(price = 123.5, salePercent = 30, name = "IPhone Case"),
        Product(price = 124.5, salePercent = 15, name = "Samsung Case"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.printProductsInfo()
    }

    override fun print(price: Double) {
        Log.d("Print", "$price")
    }

    override fun print(message: String) {
        Log.d("Print", message)
    }
}
