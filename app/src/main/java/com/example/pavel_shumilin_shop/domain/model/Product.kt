package com.example.pavel_shumilin_shop.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product (
    val id: String,
    val name: String,
    val price: Money,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>
): Parcelable {

    @Parcelize
    data class Attribute(
        val name: String,
        val value: String
    ) : Parcelable

    override fun toString(): String {
        return "$id: $name"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Product) {
            return other.id == id
        }
        return false
    }

    override fun hashCode(): Int {
        return "Entity$id".hashCode()
    }

    fun calcDiscountPrice(): Double {
        return price * (1 - (discountPercent / 100.0))
    }
}

typealias Money = Double