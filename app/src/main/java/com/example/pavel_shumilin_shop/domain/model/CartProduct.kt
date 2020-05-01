package com.example.pavel_shumilin_shop.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CartProduct internal constructor(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val lot: Lot
) : Parcelable {

    override fun toString(): String {
        return "$id: $title"
    }

    override fun equals(other: Any?): Boolean {
        if (other is CartProduct) {
            return other.id == id
        }
        return false
    }

    override fun hashCode(): Int {
        return "Entity$id".hashCode()
    }
}

@Parcelize
class Lot internal constructor(
    /**
     * [price] must be positive
     */
    val price: Double,
    /**
     * [salePercent] must between 0 and 100
     */
    val salePercent: Int
) : Parcelable {
    fun calcDiscountPrice(): Double {
        return price * (1 - (salePercent / 100.0))
    }
}

class CartProductFactory {
    fun createCartProduct(
        id: Int,
        title: String,
        imageUrl: String,
        price: Double,
        salePercent: Int
    ): CartProduct {
        val lot = Lot(price, salePercent)
        return CartProduct(id, title, imageUrl, lot)
    }
}