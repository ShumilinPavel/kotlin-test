package com.example.pavel_shumilin_shop

import org.junit.Test

import java.util.*
import kotlin.math.truncate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun example() {

        val iphoneCase = Product(price = 123.5, salePercent = 30)

        var pricePrinter: PricePrinter = CleanKotlinPricePrinter()

        val discountIphoneCasePrice = iphoneCase.calcDiscountPrice()
        pricePrinter.print(discountIphoneCasePrice)

        pricePrinter = MockPricePrinter()
        pricePrinter.print(0.0)
    }
}

class Product(
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0
) {
    /**
     * @return price with applied discount determined by [salePercent]
     *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)
}

interface PricePrinter {

    /**
     * Outputs price in <PRICE>₽ format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)
}

class CleanKotlinPricePrinter: PricePrinter {
    override fun print(price: Double) {
        if (price == truncate(price)) {
            println("${price.toInt()}₽")
        }
        else {
            println("%.2f₽".format(price))
        }
    }
}

class MockPricePrinter : PricePrinter {
    override fun print(price: Double) {
        println("9999999₽")
    }
}
