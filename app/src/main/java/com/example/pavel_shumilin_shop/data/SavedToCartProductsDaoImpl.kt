package com.example.pavel_shumilin_shop.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.pavel_shumilin_shop.domain.SavedToCartProductsDao
import com.example.pavel_shumilin_shop.domain.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SavedToCartProductsDaoImpl(
    private val sharedPreferences: SharedPreferences
): SavedToCartProductsDao {

    private val gson = Gson()

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type

    private var savedProducts: MutableList<Product>
        get() {
            val json = sharedPreferences.getString(CART_TAG, null)
            return if (json != null) {
                val type = genericType<MutableList<Product>>()
                gson.fromJson(json, type)
            } else mutableListOf()
        }
        set(value) {
            sharedPreferences.edit {
                val str = gson.toJson(value)
                putString(CART_TAG, str)
            }
        }

    override fun saveProduct(product: Product) {
        val alreadySavedProducts: MutableList<Product> = savedProducts
        alreadySavedProducts.add(product)
        savedProducts = alreadySavedProducts
    }

    override fun removeProduct(product: Product) {
        val products = savedProducts
        products.remove(product)
        savedProducts = products
    }

    override fun getAllProducts(): List<Product>  = savedProducts

    companion object {
        private const val CART_TAG = "CART_TAG"
    }
}