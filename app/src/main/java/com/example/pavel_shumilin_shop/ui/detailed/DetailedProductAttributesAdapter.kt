package com.example.pavel_shumilin_shop.ui.detailed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pavel_shumilin_shop.R
import com.example.pavel_shumilin_shop.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_attribute.*

class DetailedProductAttributesAdapter: RecyclerView.Adapter<DetailedProductAttributesAdapter.ViewHolder>() {

    private var attributes: List<Product.Attribute> = listOf()

    fun setData(attributes: List<Product.Attribute>) {
        this.attributes = attributes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_attribute, parent, false)
        )

    override fun getItemCount(): Int = attributes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(attributes[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(attribute: Product.Attribute) {
            tvAttributeName.text = attribute.name
            tvAttributeValue.text = attribute.value
        }
    }
}