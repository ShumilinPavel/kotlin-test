package com.example.pavel_shumilin_shop.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product.*


class CategoryProductsAdapter(
    private val onCardViewClick: (Product) -> Unit
): RecyclerView.Adapter<CategoryProductsAdapter.ViewHolder>() {

    private var products: List<Product> = listOf()

    fun setData(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(product: Product) {
            itemProductName.text = product.name
            Glide
                .with(itemProductImage.context)
                .load(product.imageUrl)
                .error(R.mipmap.ic_launcher)
                .into(itemProductImage)
            cardView.setOnClickListener {
                onCardViewClick(product)
            }
        }
    }
}