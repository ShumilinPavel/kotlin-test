package com.example.pavel_shumilin_shop.ui

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.data.ViewedProductDaoImpl
import com.example.pavel_shumilin_shop.domain.MainApi
import com.example.pavel_shumilin_shop.domain.model.CartProduct
import com.example.pavel_shumilin_shop.domain.model.CartProductFactory
import com.example.pavel_shumilin_shop.presenter.CartPresenter
import com.example.pavel_shumilin_shop.presenter.CartView
import com.example.pavel_shumilin_shop.ui.DetailedActivity.Companion.PRODUCT_TAG
import kotlinx.android.synthetic.main.cart_layout.*
import kotlinx.android.synthetic.main.catalog_layout.*
import moxy.ktx.moxyPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CartActivity : BaseActivity(), CartView {

    private val presenter by moxyPresenter {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://207.254.71.167:9191")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MainApi::class.java)
        CartPresenter(
            mainApi = service,
            viewedProductDao = ViewedProductDaoImpl(sharedPreferences)
        )
    }
    private val adapter =  CartProductsAdapter (
        { product ->
            presenter.removeProduct(product)
        },
        { product ->
            presenter.onProductClick(product)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        setListeners()
        cartProductsRv.layoutManager = LinearLayoutManager(this)
        cartProductsRv.adapter = adapter
    }

    private fun setListeners() {
        cartBack.setOnClickListener {
            finish()
        }

        cartCheckoutBtn.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }

        val factory = CartProductFactory()
        cartAddProductItemBtn.setOnClickListener {
            presenter.addProduct(
               factory.createCartProduct(999, "Новый продукт", "123321", 1200.0, 0)
            )
        }
    }

    override fun setProductItems(products: List<CartProduct>) {
        adapter.setData(products)
    }

    override fun removeProductItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun addProductItem(position: Int) {
        adapter.notifyItemInserted(position)
    }

    override fun showProductDetailed(product: CartProduct) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(PRODUCT_TAG, product)
        })
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}