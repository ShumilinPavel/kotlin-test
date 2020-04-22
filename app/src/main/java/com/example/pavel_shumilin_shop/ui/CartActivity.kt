package com.example.pavel_shumilin_shop.ui

import android.content.Intent
import android.os.Bundle
import com.example.pavel_shumilin_shop.R
import kotlinx.android.synthetic.main.cart_layout.*

class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        cartBack.setOnClickListener {
            finish()
        }

        cartCheckoutBtn.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }
    }
}