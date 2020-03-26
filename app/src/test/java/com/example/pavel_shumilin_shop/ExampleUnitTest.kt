package com.example.pavel_shumilin_shop

import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.math.sqrt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

//    fun doSomething(arg1: Int, arg2: String = "2"): String {
//        return "Hello world! $arg1, $arg2"
//    }
//
//    fun solveEquation(a: Double, b: Double, c: Double) {
//        val discr = Math.pow(b, 2.0) - 4 * a * c
//        val x1 = (-b + sqrt(discr)) / (2 * a)
//        val x2 = (-b - sqrt(discr)) / (2 * a)
//        println(x1)
//        println(x2)
//    }

    /**
     * Форматирует цену
     */
    fun printFormatPrice(price: Double, measure: String = "шт", discount: Int) {
        val discountPrice = price * (1 - discount / 100.0)
        val discountPriceFormat = if (discountPrice % 1 != 0.0) ".2" else ".0"
        System.out.printf(Locale.ENGLISH, "%${discountPriceFormat}f/%s", discountPrice, measure)
        if (discount != 0)
            System.out.printf(Locale.ENGLISH, " (скидка %d%%)", discount)
        println()
    }

    fun PriceFormat(price : Double, measure: String, discount: Int = 0) : String{
        val finalPrice : String
        if ((discount > 0) and (discount <= 100)) {
            //вычисляем цену по скидке
            val discountPrice : Double = ((100 - discount)*price)/100
            //определяем, целое число или дробное
            if (Math.floor(discountPrice) == (discountPrice)) {
                //число целое - цена без дробной части
                finalPrice  = Math.floor(discountPrice).toInt().toString()
            } else {
                //число дробное - цена с дробной частью
                finalPrice = String.format("%.2f", discountPrice)
            }
            return "$finalPrice/$measure (скидка $discount%)"
        }
        else if (discount == 0) {
            //определяем, целое число или дробное
            if (Math.floor(price) == (price)) {
                //число целое - цена без дробной части
                finalPrice  = Math.floor(price).toInt().toString()
            } else {
                //число дробное - цена с дробной частью
                finalPrice = String.format("%.2f", price)
            }
            //выводим цену без скидки
            return "$finalPrice/$measure"
        }
        else {
            return "Скидка не может принимать значение меньше 0 или больше 100"
        }
    }

    @Test
    fun printDiscount() {
        printFormatPrice(120.0, "кг", 5)
        printFormatPrice(39.0,  discount =  7)
        printFormatPrice(56.0,  "re", 0)
    }
}
