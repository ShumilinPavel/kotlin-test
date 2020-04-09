package com.example.pavel_shumilin_shop

class Product (
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0,
    private val name: String
) {
    /**
    * @return price with applied discount determined by [salePercent]
    *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)

    fun getName(): String = name
}