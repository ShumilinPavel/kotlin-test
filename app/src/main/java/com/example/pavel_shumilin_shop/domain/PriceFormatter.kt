package com.example.pavel_shumilin_shop.domain

import java.util.*
import kotlin.math.truncate

class PriceFormatter {
    /**
     * Outputs price in <PRICE>₽ format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun format(price: Double): String {
        return if (price == truncate(price)) {
            "${price.toInt()}₽"
        }
        else {
           String.format("%.2f₽".format(Locale.ENGLISH, price))
        }
    }
}