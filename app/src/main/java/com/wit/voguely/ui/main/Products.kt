package com.wit.voguely.ui.main

import com.wit.voguely.R

class Products(
    var image: Int,
    var name: String,
    var price: String,
    var rating: String,
    var review: String
)

var productOne: Products = Products(
    R.drawable.watch,
    "Smart Watch",
    "100 EUR",
    "4.8",
    "231 Reviews"
)
var productTwo: Products = Products(
    R.drawable.headphoneswhite,
    "Headphones Soundcore",
    "200 EUR",
    "5.0",
    "905 Reviews"
)

var productThree: Products = Products(
    R.drawable.phone,
    "Phone Samsung",
    "800 EUR",
    "3.9",
    "71 Reviews"
)
var productFour: Products = Products(
    R.drawable.headphoneswhite,
    "Hairdryer Soundcore",
    "200 EUR",
    "5.0",
    "905 Reviews"
)
var productFive: Products = Products(
    R.drawable.watch,
    "Lamp",
    "100 EUR",
    "4.8",
    "231 Reviews"
)
var productSix: Products = Products(
    R.drawable.phone,
    "Humidifyer",
    "800 EUR",
    "3.9",
    "71 Reviews"
)
var productSeven: Products = Products(
    R.drawable.phone,
    "Mop Samsung",
    "800 EUR",
    "3.9",
    "71 Reviews"
)
var productEight: Products = Products(
    R.drawable.headphoneswhite,
    "Charger Soundcore",
    "200 EUR",
    "5.0",
    "905 Reviews"
)
var productNine: Products = Products(
    R.drawable.watch,
    "Keybord",
    "100 EUR",
    "4.8",
    "231 Reviews"
)
var productTen: Products = Products(
    R.drawable.phone,
    "Humidifyer",
    "800 EUR",
    "3.9",
    "71 Reviews"
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