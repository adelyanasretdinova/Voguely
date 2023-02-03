package com.wit.voguely.ui.main.cart

class TotalPriceCalculator {

    fun getTotalPrice(item: List<ProductInCart>):Int {
        return item.sumOf {
            if(it.product.price < 0 || it.quantity < 0) {
                0
            } else
            {it.product.price * it.quantity }
        }
    }
}