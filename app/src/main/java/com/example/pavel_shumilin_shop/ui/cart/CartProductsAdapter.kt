package com.example.pavel_shumilin_shop.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.PriceFormatter
import com.example.pavel_shumilin_shop.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cart.*


class CartProductsAdapter(
    private val onDeleteClick: (product: Product) -> (Unit),
    private val onProductClick: (product: Product) -> (Unit)
) : RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    private var products: List<Product> = listOf()
    private val priceFormatter = PriceFormatter()

    fun setData(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(product: Product) {
            itemCartName.text = product.name
            itemCartPrice.text = priceFormatter.format(product.calcDiscountPrice())
            itemCartDiscount.text = priceFormatter.format(product.price - product.calcDiscountPrice())

            itemCartDelete.setOnClickListener {
                onDeleteClick(product)
            }
            containerView.setOnClickListener {
                onProductClick(product)
            }
        }
    }
}