package com.example.pavel_shumilin_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pavel_shumilin_shop.Product
import com.example.pavel_shumilin_shop.R
import kotlinx.android.synthetic.main.item_cart.view.*

class CartProductsAdapter(
    private val onDeleteClick: (product: Product) -> (Unit)
) : RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    private var products: List<Product> = listOf()

    fun setData(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductsAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            itemView.itemCartName.text = product.getName()
            itemView.itemCartPrice.text = product.calcDiscountPrice().toString()
            itemView.itemCartDiscount.text = (product.getPrice() - product.calcDiscountPrice()).toString()

            itemView.itemCartDelete.setOnClickListener {
                onDeleteClick(product)
            }
        }
    }
}