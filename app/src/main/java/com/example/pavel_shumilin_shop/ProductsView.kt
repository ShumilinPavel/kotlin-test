package com.example.pavel_shumilin_shop

interface ProductsView {
    /**
     * Outputs price in <PRICE>₽ format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)

    fun print(message: String)
}