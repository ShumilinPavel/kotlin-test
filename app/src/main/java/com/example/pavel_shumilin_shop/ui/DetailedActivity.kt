package com.example.pavel_shumilin_shop.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.data.ViewedProductDaoImpl
import com.example.pavel_shumilin_shop.domain.model.CartProduct
import com.example.pavel_shumilin_shop.presenter.DetailedPresenter
import com.example.pavel_shumilin_shop.presenter.DetailedView
import kotlinx.android.synthetic.main.detailed_layout.*
import moxy.ktx.moxyPresenter

class DetailedActivity : BaseActivity(), DetailedView {

    private val presenter by moxyPresenter {
        DetailedPresenter(
            ViewedProductDaoImpl(sharedPreferences)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_layout)

        val product = intent?.getParcelableExtra<CartProduct>(PRODUCT_TAG) ?: return
        Glide
            .with(ivDetailedImage.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(ivDetailedImage)
        tvDetailedTitle.text = product.title
        tvDetailedPrice.text = product.lot.calcDiscountPrice().toString()

        presenter.onProductShow(product)
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}