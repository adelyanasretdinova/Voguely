package com.wit.voguely.ui.main

import com.google.firebase.database.IgnoreExtraProperties
import com.wit.voguely.R

@IgnoreExtraProperties
data class Products(
    val id:String = "",
    val image: String = "",
    val name: String = "",
    val price: Int = 0,
    val rating: Double = 0.0,
    val reviews: Int = 0,
    val currency: String = "",
    val description:String = ""
)



class ProductsInCart(
    var image: Int,
    var name: String,
    var price: String,
)

var productCartOne: ProductsInCart = ProductsInCart(
    R.drawable.watch,
    "Humidifyer",
    "800 EUR",
)

var productCartTwo: ProductsInCart = ProductsInCart(
    R.drawable.phone,
    "Phone",
    "500 EUR",
)
var productCartThree: ProductsInCart = ProductsInCart(
    R.drawable.headphoneswhite,
    "Humidifyer",
    "800 EUR",
)

var productCartFour: ProductsInCart = ProductsInCart(
    R.drawable.watch,
    "Phone",
    "100 EUR",
)