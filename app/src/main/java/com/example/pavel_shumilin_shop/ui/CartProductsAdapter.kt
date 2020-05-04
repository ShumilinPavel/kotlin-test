package com.example.pavel_shumilin_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.PriceFormatter
import com.example.pavel_shumilin_shop.domain.model.CartProduct
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cart.*


class CartProductsAdapter(
    private val onDeleteClick: (product: CartProduct) -> (Unit),
    private val onProductClick: (product: CartProduct) -> (Unit)
) : RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    private var products: List<CartProduct> = listOf()
    private val priceFormatter = PriceFormatter()

    fun setData(products: List<CartProduct>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductsAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(product: CartProduct) {
            itemCartName.text = product.title
            itemCartPrice.text = priceFormatter.format(product.lot.calcDiscountPrice())
            itemCartDiscount.text = priceFormatter.format(product.lot.price - product.lot.calcDiscountPrice())

            itemCartDelete.setOnClickListener {
                onDeleteClick(product)
            }
            containerView.setOnClickListener {
                onProductClick(product)
            }
        }
    }
}