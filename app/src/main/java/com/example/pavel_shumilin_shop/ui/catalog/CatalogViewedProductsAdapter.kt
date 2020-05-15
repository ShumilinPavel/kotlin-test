package com.example.pavel_shumilin_shop.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pavel_shumilin_shop.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_viewed_product.*

class CatalogViewedProductsAdapter :
    RecyclerView.Adapter<CatalogViewedProductsAdapter.ViewHolder>() {

    private var viewedProductIds: List<Long> = emptyList()

    fun setData(productIds: List<Long>) {
        viewedProductIds = productIds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_viewed_product,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = viewedProductIds.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productId = viewedProductIds[position]
        holder.bind(productId)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(productId: Long) {
            viewedProductId.text = productId.toString()
//            viewedProductName.text = product.title
//            viewedProductPrice.text = product.lot.calcDiscountPrice().toString()
        }
    }
}