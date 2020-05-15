package com.example.pavel_shumilin_shop.ui.catalog

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pavel_shumilin_shop.App
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.catalog.CatalogPresenter
import com.example.pavel_shumilin_shop.presenter.catalog.CatalogView
import com.example.pavel_shumilin_shop.ui.BaseActivity
import com.example.pavel_shumilin_shop.ui.CategoryProductsAdapter
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

//    private val categoriesAdapter = CatalogCategoriesAdapter { category ->
//        presenter.removeItem(category)
//    }

//    private val viewedProductsAdapter = CatalogViewedProductsAdapter()

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

//        Log.d(tag, "savedInstanceState = $savedInstanceState")
//        val savedInt = savedInstanceState?.getInt(SAVE_SATE_INT)
//        Log.d(tag, "savedInt $savedInt")

        catalogCartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java).apply {
                putExtra(PRODUCT_ID, 1000)
            }
            startActivityForResult(intent,
                REQUEST_AUTH
            )
        }

        catalogProductsRv.layoutManager = GridLayoutManager(this, 2)
        catalogProductsRv.adapter = categoryProductsAdapter
//        viewedProductsRv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
//        viewedProductsRv.adapter = viewedProductsAdapter
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt(SAVE_SATE_INT, 89)
//        super.onSaveInstanceState(outState)
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (REQUEST_AUTH == requestCode) {
            val isUserAuth = data?.extras?.getBoolean(IS_USER_AUTH)
            Log.d(tag, "onActivityResult ${isUserAuth.toString()}")
        }
    }

    override fun setCategoryProducts(list: List<Product>) {
        categoryProductsAdapter.setData(list)
    }

    override fun removeItem(position: Int) {
//        categoriesAdapter.notifyItemRemoved(position)
    }

    override fun showProductIds(productIds: List<Long>) {
        // Toast.makeText(this, productIds.joinToString(","), Toast.LENGTH_LONG).show()
//        viewedProductsAdapter.setData(productIds)
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val SAVE_SATE_INT = "SAVE_SATE_INT"
    }
}

val AppCompatActivity.sharedPreferences: SharedPreferences
    get() = getSharedPreferences("data", MODE_PRIVATE)