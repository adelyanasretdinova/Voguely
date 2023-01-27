package com.wit.voguely.ui.main

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class CartResponse(
    val productId: String = "",
    val quantity: Int = 0
)


//var productCartOne: ProductsInCart = ProductsInCart(
//    R.drawable.watch,
//    "Humidifyer",
//    "800 EUR",
//)
//
//var productCartTwo: ProductsInCart = ProductsInCart(
//    R.drawable.phone,
//    "Phone",
//    "500 EUR",
//)
//var productCartThree: ProductsInCart = ProductsInCart(
//    R.drawable.headphoneswhite,
//    "Humidifyer",
//    "800 EUR",
//)
//
//var productCartFour: ProductsInCart = ProductsInCart(
//    R.drawable.watch,
//    "Phone",
//    "100 EUR",
//)