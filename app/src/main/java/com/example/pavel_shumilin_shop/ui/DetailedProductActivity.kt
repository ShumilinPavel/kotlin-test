package com.example.pavel_shumilin_shop.ui

import android.os.Bundle
import com.example.pavel_shumilin_shop.R
import kotlinx.android.synthetic.main.detailed_product_layout.*

class DetailedProductActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_product_layout)

        detailedProductBack.setOnClickListener {
            finish()
        }
    }
}