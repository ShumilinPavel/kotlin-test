package com.example.pavel_shumilin_shop.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.example.pavel_shumilin_shop.presenter.CheckoutView
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.model.CartProductFactory
import com.example.pavel_shumilin_shop.presenter.CheckoutPresenter
import kotlinx.android.synthetic.main.checkout_layout.*
import moxy.ktx.moxyPresenter

class CheckoutActivity : BaseActivity(),
    CheckoutView {
    private val presenter by moxyPresenter { CheckoutPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_layout)

        setListeners()

        val factory = CartProductFactory()

        val products = listOf(
            factory.createCartProduct(1, "IPhone Case", "image_url", 123.5, 30),
            factory.createCartProduct(1, "Samsung Case", "image_url", 124.5, 15)
        )

        var initialPrice = 0.0
        var discountPrice = 0.0
        products.forEach {
            initialPrice += it.lot.price
            discountPrice += it.lot.calcDiscountPrice()
        }

        checkoutProductsPrice.text = initialPrice.toString()
        checkoutFinalPrice.text = discountPrice.toString()
        checkoutSaleAmount.text = (initialPrice - discountPrice).toString()
    }

    private fun setListeners() {
        checkoutBack.setOnClickListener {
            finish()
        }

        checkoutSurname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               presenter.checkSurname(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutMiddleName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkMiddleName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhoneNumber(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

//        checkoutPhone.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
//            if (!hasFocus) presenter.checkPhoneNumber(checkoutPhone.text.toString())
//        }
    }

//    override fun print(price: Double) {
//        Log.d("Print", "$price")
//    }
//
//    override fun print(message: String) {
//        Log.d("Print", message)
//    }

    override fun showErrorForSurname(visible: Boolean) {
        checkoutSurname.showError(visible)
    }

    override fun showErrorForName(visible: Boolean) {
        checkoutName.showError(visible)
    }

    override fun showErrorForMiddleName(visible: Boolean) {
        checkoutMiddleName.showError(visible)
    }

    override fun showErrorForPhone(visible: Boolean) {
        checkoutPhone.showError(visible)
    }

    private fun EditText.showError(visible: Boolean) {
        val drawable = if (visible) R.drawable.ic_error else 0
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }
}
