package com.example.pavel_shumilin_shop.ui.detailed

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pavel_shumilin_shop.App
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.PriceFormatter
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.detailed.DetailedPresenter
import com.example.pavel_shumilin_shop.presenter.detailed.DetailedView
import com.example.pavel_shumilin_shop.ui.BaseActivity
import com.example.pavel_shumilin_shop.ui.cart.CartActivity
import kotlinx.android.synthetic.main.detailed_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailedActivity : BaseActivity(), DetailedView {

    @Inject
    lateinit var detailedPresenter: DetailedPresenter

    private val presenter by moxyPresenter { detailedPresenter }

    private val adapter =
        DetailedProductAttributesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_layout)

        setToolbar()

        val product = intent?.getParcelableExtra<Product>(PRODUCT_TAG) ?: return

        setProductInfo(product)
        setListenersForProduct(product)
        setAdapter()
    }

    private fun setToolbar() {
         setSupportActionBar(detailed_toolbar)
        detailed_toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.title = "Детальная информация"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.toCartBtn) {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    private fun setProductInfo(product: Product) {
        val formatter = PriceFormatter()

        Glide
            .with(ivDetailedImage.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(ivDetailedImage)
        tvDetailedTitle.text = product.name
        tvDetailedPrice.text = formatter.format(product.calcDiscountPrice())
    }

    private fun setListenersForProduct(product: Product) {
        presenter.onProductShow(product)

        addToCartButton.setOnClickListener {
            presenter.onAddToCartButtonClick(product)
            Toast.makeText(this, "Добавлено в коризну", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAdapter() {
        attributesRv.layoutManager = LinearLayoutManager(this)
        attributesRv.isNestedScrollingEnabled = false
        attributesRv.adapter = adapter
    }

    override fun setAttributeItems(attributes: List<Product.Attribute>) {
        adapter.setData(attributes)
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}