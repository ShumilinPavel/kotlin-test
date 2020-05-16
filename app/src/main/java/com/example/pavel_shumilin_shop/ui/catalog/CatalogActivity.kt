package com.example.pavel_shumilin_shop.ui.catalog

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pavel_shumilin_shop.App
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.catalog.CatalogPresenter
import com.example.pavel_shumilin_shop.presenter.catalog.CatalogView
import com.example.pavel_shumilin_shop.ui.BaseActivity
import com.example.pavel_shumilin_shop.ui.detailed.DetailedActivity
import com.example.pavel_shumilin_shop.ui.detailed.DetailedActivity.Companion.PRODUCT_TAG
import com.example.pavel_shumilin_shop.ui.cart.CartActivity
import kotlinx.android.synthetic.main.catalog_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class CatalogActivity : BaseActivity(),
    CatalogView {

    @Inject
    lateinit var catalogPresenter: CatalogPresenter

    private val presenter by moxyPresenter { catalogPresenter }

    private val categoryProductsAdapter =
        CategoryProductsAdapter { product ->
            val intent = Intent(
                this,
                DetailedActivity::class.java
            ).apply {
                putExtra(PRODUCT_TAG, product)
            }
            startActivity(intent)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)

        setToolbar()
        setAdapter()
    }

    private fun setToolbar() {
        setSupportActionBar(catalog_toolbar)
        catalog_toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.title = "Каталог"
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

    private fun setAdapter() {
        catalogProductsRv.layoutManager = GridLayoutManager(this, 2)
        catalogProductsRv.adapter = categoryProductsAdapter
    }

    override fun setCategoryProducts(list: List<Product>) {
        categoryProductsAdapter.setData(list)
    }
}