package com.example.pavel_shumilin_shop.ui.detailed

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pavel_shumilin_shop.App
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.model.Product
import com.example.pavel_shumilin_shop.presenter.detailed.DetailedPresenter
//import com.example.pavel_shumilin_shop.presenter.detailed.DetailedPresenter.DetailedPresenterFactory
import com.example.pavel_shumilin_shop.presenter.detailed.DetailedView
import com.example.pavel_shumilin_shop.ui.BaseActivity
import kotlinx.android.synthetic.main.detailed_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailedActivity : BaseActivity(),
    DetailedView {

//    @Inject
//    lateinit var detailedPresenterFactory: DetailedPresenterFactory

//    private val presenter by moxyPresenter { detailedPresenterFactory.create(product) }

    @Inject
    lateinit var detailedPresenter: DetailedPresenter

    private val presenter by moxyPresenter { detailedPresenter }

    private val adapter =
        DetailedProductAttributesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_layout)


        val product = intent?.getParcelableExtra<Product>(PRODUCT_TAG) ?: return
        Glide
            .with(ivDetailedImage.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(ivDetailedImage)
        tvDetailedTitle.text = product.name
        tvDetailedPrice.text = product.calcDiscountPrice().toString()

        presenter.onProductShow(product)

        addToCartButton.setOnClickListener {
            presenter.onAddToCartButtonClick(product)
            Toast.makeText(this, "Добавлено в коризну", Toast.LENGTH_SHORT).show()
        }

        attributesRv.layoutManager = LinearLayoutManager(this)
        attributesRv.isNestedScrollingEnabled = false
        attributesRv.adapter = adapter
    }

    override fun setAttributeItems(attribtes: List<Product.Attribute>) {
        adapter.setData(attribtes)
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}