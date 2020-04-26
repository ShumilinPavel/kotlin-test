package com.example.pavel_shumilin_shop.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.example.pavel_shumilin_shop.Product
import com.example.pavel_shumilin_shop.ProductsView
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.presenter.CheckoutPresenter
import kotlinx.android.synthetic.main.checkout_layout.*

class CheckoutActivity : BaseActivity(), ProductsView {
    private val presenter =
        CheckoutPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_layout)

        presenter.attachView(this)
        setListeners()

        val products = listOf(
            Product(
                price = 123.5,
                salePercent = 30,
                name = "IPhone Case"
            ),
            Product(
                price = 124.5,
                salePercent = 15,
                name = "Samsung Case"
            )
        )

        var initialPrice = 0.0
        var discountPrice = 0.0
        products.forEach {
            initialPrice += it.getPrice()
            discountPrice += it.calcDiscountPrice()
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

    override fun print(price: Double) {
        Log.d("Print", "$price")
    }

    override fun print(message: String) {
        Log.d("Print", message)
    }

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
