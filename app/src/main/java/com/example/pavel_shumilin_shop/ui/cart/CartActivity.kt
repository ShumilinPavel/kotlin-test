package com.example.pavel_shumilin_shop.ui.cart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pavel_shumilin_shop.App
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.cart.CartPresenter
import com.example.pavel_shumilin_shop.presenter.cart.CartView
import com.example.pavel_shumilin_shop.ui.BaseActivity
import com.example.pavel_shumilin_shop.ui.checkout.CheckoutActivity
import com.example.pavel_shumilin_shop.ui.detailed.DetailedActivity
import com.example.pavel_shumilin_shop.ui.detailed.DetailedActivity.Companion.PRODUCT_TAG
import kotlinx.android.synthetic.main.cart_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CartActivity : BaseActivity(),
    CartView {

    @Inject
    lateinit var cartPresenter: CartPresenter

    private val presenter by moxyPresenter { cartPresenter }

    private val adapter =
        CartProductsAdapter(
            { product ->
                presenter.removeProduct(product)
            },
            { product ->
                presenter.onProductClick(product)
            }
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
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
            presenter.onCartCheckoutBtnClick()
//            val intent = Intent(this, CheckoutActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun goToCheckoutActivity(productsInCart: List<Product>) {
        val intent = Intent(this, CheckoutActivity::class.java)
        val x = ArrayList(productsInCart)
        intent.apply {
            putParcelableArrayListExtra(PRODUCT_TAG, x)
        }
        startActivity(intent)
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

    override fun showProductDetailed(product: Product) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(PRODUCT_TAG, product)
        })
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}