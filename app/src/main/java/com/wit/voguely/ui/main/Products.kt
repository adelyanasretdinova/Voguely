package com.wit.voguely.ui.main

import com.wit.voguely.R

class Products (
    var image:Int = R.drawable.headphoneswhite,
    var name:String = "Headphones Sony",
    var price: String = "100 EUR",
    var rating: String = "4.8",
    var review: String = "231 Reviews"
        )

var productTwo: Products = Products(
    R.drawable.headphoneswhite,
    "Headphones Soundcore",
    "200 EUR",
    "5.0",
    "905 Reviews"
)
var productOne: Products = Products(
    R.drawable.headphonessmall,
    "Headphones Sony",
    "100 EUR",
    "4.8",
    "231 Reviews"
)