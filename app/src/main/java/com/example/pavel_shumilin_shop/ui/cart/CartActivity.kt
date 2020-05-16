package com.example.pavel_shumilin_shop.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
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

class CartActivity : BaseActivity(), CartView {

    @Inject
    lateinit var cartPresenter: CartPresenter

    private val presenter by moxyPresenter { cartPresenter }

    private val adapter =
        CartProductsAdapter(
            { product ->
                presenter.onRemoveProduct(product)
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
        setToolbar()
        setAdapter()
    }

    private fun setListeners() {
        cartCheckoutBtn.setOnClickListener {
            presenter.onCartCheckoutBtnClick()
        }
    }

    private fun setToolbar() {
        setSupportActionBar(cart_toolbar)
        cart_toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.title = "Корзина"
    }

    private fun setAdapter() {
        cartProductsRv.layoutManager = LinearLayoutManager(this)
        cartProductsRv.adapter = adapter
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
        val intent = Intent(this, DetailedActivity::class.java)
        intent.apply {
            putExtra(PRODUCT_TAG, product)
        }
        startActivity(intent)
    }

    override fun showCartCheckout(productsInCart: List<Product>) {
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.apply {
            putParcelableArrayListExtra(PRODUCT_TAG, ArrayList(productsInCart))
        }
        startActivity(intent)
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showNoCartProductsTextView() {
        val tv = TextView(this)
        tv.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        tv.text = "Корзина пуста"
        tv.id = ViewCompat.generateViewId()

        cart_cl.addView(tv)

        val cs = ConstraintSet()
        cs.clone(cart_cl)

        cs.connect(tv.id, ConstraintSet.TOP, cart_cl.id, ConstraintSet.TOP, 18)
        cs.connect(tv.id, ConstraintSet.BOTTOM, cart_cl.id, ConstraintSet.BOTTOM, 18)
        cs.connect(tv.id, ConstraintSet.LEFT, cart_cl.id, ConstraintSet.LEFT, 18)
        cs.connect(tv.id, ConstraintSet.RIGHT, cart_cl.id, ConstraintSet.RIGHT, 18)

        cs.applyTo(cart_cl)

        val a = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        a.reset()
        tv.clearAnimation()
        tv.startAnimation(a)
    }
}