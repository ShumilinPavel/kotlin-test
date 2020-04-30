package com.example.pavel_shumilin_shop

import com.example.pavel_shumilin_shop.domain.model.Product
import org.junit.Test
import java.util.*
import kotlin.math.truncate


class ExampleUnitTest {
    @Test
    fun example() {
        val presenter = Presenter()
        presenter.pricePrint()
    }
}

class Presenter {
    private val iphoneCase =
        com.example.pavel_shumilin_shop.domain.model.Product(
            price = 123.5,
            salePercent = 30,
            productName = "IPhone Case"
        )
    private val samsungCase =
        com.example.pavel_shumilin_shop.domain.model.Product(
            price = 124.5,
            salePercent = 15,
            productName = "Samsung Case"
        )
    private val products = listOf(iphoneCase, samsungCase)
    private val shoppingCart = ShoppingCart(products)
    private val shoppingCartPrinter : ShoppingCartPrinter = ShoppingCartConsolePrinter()
    private val pricePrinter = ConsolePricePrinter()

    fun pricePrint() {
        // shoppingCartPrinter.print(shoppingCart)
        // productNamePrint()
        printProductInfo()
    }

    fun productNamePrint() {
        products.forEach {
            pricePrinter.print(it.getProductName())
        }
    }

    fun printProductInfo() {
        products.forEach {
            println("${it.getProductName()}: ${it.calcDiscountPrice()}")
        }
    }
}

class Product(
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0,
    private val productName: String
) {
    /**
     * @return price with applied discount determined by [salePercent]
     *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)

    fun getProductName(): String = productName
}

class ShoppingCart (
    private val products: List<Product>
) {
    /**
     * @return total price of products from the shopping cart with the applied discount
     */
    fun calcTotalPrice(): Double {
        var totalPrice = 0.0
        products.forEach {
            totalPrice += it.calcDiscountPrice()
        }
        return totalPrice
    }

    fun getProducts(): List<Product> = products
}

interface ShoppingCartPrinter {
    fun print(shoppingCart: ShoppingCart)
}

class ShoppingCartConsolePrinter : ShoppingCartPrinter {
    override fun print(shoppingCart: ShoppingCart) {
        val pricePrinter = ConsolePricePrinter()
        shoppingCart.getProducts().forEach {
            pricePrinter.print(it.calcDiscountPrice())
        }
        print("Total price: ")
        pricePrinter.print(shoppingCart.calcTotalPrice())
    }
}

interface PricePrinter {
    /**
     * Outputs price in <PRICE>₽ format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)

    fun print(name: String)
}

class ConsolePricePrinter : PricePrinter {
    override fun print(price: Double) {
        if (price == truncate(price)) {
            println("${price.toInt()}₽")
        }
        else {
            println("%.2f₽".format(Locale.ENGLISH, price))
        }
    }

    override fun print(name: String) {
        println(name)
    }
}