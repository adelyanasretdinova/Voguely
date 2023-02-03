package com.wit.voguely

import com.wit.voguely.model.Products
import com.wit.voguely.ui.main.cart.ProductInCart
import com.wit.voguely.ui.main.cart.TotalPriceCalculator
import org.junit.Assert
import org.junit.Test

class TotalPriceCalculatorTest {

    val totalPriceCalculator = TotalPriceCalculator()

    @Test
    fun isTotalPriceCorrect() {
        val cartItemList = listOf(
            ProductInCart(
                quantity = 1,
                product = Products(
                    price = 20
                )
            ),
            ProductInCart(
                quantity = 5,
                product = Products(
                    price = 10
                )
            )
        )
        val totalTotalPrice = totalPriceCalculator.getTotalPrice((cartItemList))
        Assert.assertEquals(70, totalTotalPrice)
    }

    @Test
    fun isTotalPriceForEmptyCorrect() {
        val totalPrice = totalPriceCalculator.getTotalPrice(listOf())
    Assert.assertEquals(0, totalPrice)
    }

    @Test
    fun isTotalPriceForNegativePriceCorrect() {
        val cartItems = listOf(
            ProductInCart(
                quantity = 1,
                product = Products(
                    price = -20
                )
            ),
            ProductInCart(
                quantity = 5,
                product = Products(
                    price = 20
                )
            ),
            ProductInCart(
                quantity = 1,
                product = Products(
                    price = -50
                )
            )
        )

        val totalPrice = totalPriceCalculator.getTotalPrice(cartItems)
        Assert.assertEquals(100, totalPrice)
    }

        @Test
fun isTotalPriceForNegativeQuantityCorrect() {
            val cartItems = listOf(
                ProductInCart(
                    quantity = 2,
                    product = Products(
                        price = 20
                    )
                ),
                ProductInCart(
                    quantity = -10,
                    product = Products(
                        price = 20
                    )
                )
            )
            val totalPrice = totalPriceCalculator.getTotalPrice(cartItems)
            Assert.assertEquals(40, totalPrice)

}
}