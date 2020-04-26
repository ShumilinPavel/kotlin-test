package com.example.pavel_shumilin_shop.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pavel_shumilin_shop.Product
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.presenter.CartPresenter
import kotlinx.android.synthetic.main.cart_layout.*

class CartActivity : BaseActivity(), CartView {

    private val presenter = CartPresenter()
    private val adapter =  CartProductsAdapter {  product ->
        presenter.removeProduct(product)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)
        setListeners()

        cartProductsRv.layoutManager = LinearLayoutManager(this)
        cartProductsRv.adapter = adapter

        presenter.attachView(this)
        presenter.setProducts()
    }

    private fun setListeners() {
        cartBack.setOnClickListener {
            finish()
        }

        cartCheckoutBtn.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }

        cartAddProductItemBtn.setOnClickListener {
            presenter.addProduct(Product(1000.0, 10, "Новый продукт"))
        }
    }

    override fun setProductItems(products: List<Product>) {
        adapter.setData(products)
    }

    override fun removeProductItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun addProductItem(position: Int) {
        adapter.notifyItemInserted(position)
    }
}